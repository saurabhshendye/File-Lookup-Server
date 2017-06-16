/*
 * Created by Saurabh on 03/06/2017.
 */

package endPoints;

import Transport.TCPReceiver;
import Transport.TCPSender;
import WireFormats.fileResponse;
import util.argumentParser;
import util.findFile;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class Server
{
    private int port;
    private ServerSocket serverSocket;


    private static Server server = null;
    private static ConcurrentHashMap<String, TCPSender> senderMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, byte[]> cache = new ConcurrentHashMap<>();
    private static TCPSender sender;
    private static int cacheSize = 0;
    private static ConcurrentHashMap<String, Integer> cacheStats = new ConcurrentHashMap<>();


    private Server(int port) throws IOException
    {
        this.port = port;
        this.serverSocket = new ServerSocket(port,5);
    }


    public static void main(String [] args) throws IOException
    {
        // Checking the validity of the input arguments with argParser class
        argumentParser argParse = new argumentParser(args);
        if (argParse.isValid())
        {
            // Creater instance of server class
            server = new Server(argParse.port);
            System.out.println("Server Started..");

            // Getting the instance of a findFile singleton class
            // and setting the file path
//            findFile ff = findFile.getInstance();
//            ff.setPath(argParse.path);
            System.out.println("Path Set");

            while (true)
            {
                // Listening for the connections
                Socket clientSocket = server.serverSocket.accept();
                Thread receiver = new TCPReceiver(clientSocket);
                receiver.start();
                sender = new TCPSender(clientSocket);

            }
        }
        else
        {
            System.out.println("Invalid Arguments");
        }
    }

    public static Server getInstance()
    {
        return server;
    }

    public void parseRequest(String fileName) throws IOException
    {
        if (cache.containsKey(fileName))
        {
            System.out.println("Found in cache");
            sender.send_and_maintain(cache.get(fileName));
            Integer temp = cacheStats.get(fileName);
            temp += 1;
            cacheStats.put(fileName, temp);
            System.out.println("Data sent");
        }
        else
        {
            findFile ff = new findFile();
            ff.setPath(argumentParser.getPath());
            ff.fileLookup(fileName, ff.getPath());
            if(ff.isPresent())
            {
                fileResponse fr = new fileResponse(ff.getPath());
                System.out.println("Byte Array Created");
                sender.sendAndClose(fr.getByteArray());
                System.out.println("Data sent");
                server.cacheEntry(fileName, fr);
            }
            else
            {
                // Response when file is not found
            }
        }
    }

    private void cacheEntry(String fileName, fileResponse fr) throws IOException
    {
        byte[] data = fr.getByteArray();
        if (cacheSize + data.length > 67108864)
        {
            // removing the least accessed file from the
            // cache so as to prevent cache overflow
            OuterLoop:
            while (true)
            {
                Iterator it = cacheStats.entrySet().iterator();
                int min = Integer.MAX_VALUE;
                while (it.hasNext())
                {
                    ConcurrentHashMap.Entry entry = (ConcurrentHashMap.Entry) it.next();
                    if (min > (int)entry.getValue())
                    {
                        // Subtracting the value from size
                        Integer value = (int)entry.getValue();
                        cacheSize -= value;

                        // Removing the key from the hashMap
                        String key = (String) entry.getKey();
                        cacheStats.remove(key);
                    }
                    if (cacheSize + data.length < 67108864)
                    {
                        break OuterLoop;
                    }

                }
            }
            cacheSize += data.length;
            cache.put(fileName, data);
            cacheStats.put(fileName, 1);
        }
        else
        {
            // Adding the files to cache and updating the
            // cacheStats
            cacheSize += data.length;
            cache.put(fileName, data);
            cacheStats.put(fileName, 1);
        }

    }


}
