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
import java.util.concurrent.ConcurrentHashMap;

public class Server
{
    private int port;
    private ServerSocket serverSocket;
    private static Server server = null;

    private static ConcurrentHashMap<String, TCPSender> senderMap = new ConcurrentHashMap<>();
    private static TCPSender sender;


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

    public void parseRequest(String fileName) throws IOException {
        findFile ff = new findFile();
        ff.setPath(argumentParser.getPath());
        ff.fileLookup(fileName, ff.getPath());
        if(ff.isPresent())
        {
            fileResponse fr = new fileResponse(ff.getPath());
            System.out.println("Byte Array Created");
            sender.sendAndClose(fr.getByteArray());
        }
        else
        {

        }

    }

}
