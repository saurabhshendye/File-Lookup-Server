/*
 * Created by Saurabh on 03/06/2017.
 */

package endPoints;

import Transport.TCPReceiver;
import util.argumentParser;
import util.findFile;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private int port;
//    private File path;
    private ServerSocket serverSocket;

    private Server(int port) throws IOException
    {
        this.port = port;
//        this.path = path;
        this.serverSocket = new ServerSocket(port,5);
    }


    public static void main(String [] args) throws IOException
    {
        argumentParser argParse = new argumentParser(args);
        if (argParse.isValid())
        {
            Server server = new Server(argParse.port);
            findFile ff = findFile.getInstance();
            ff.setPath(argParse.path);

            while (true)
            {
                Socket clientSocket = server.serverSocket.accept();
                Thread receiver = new TCPReceiver(clientSocket);
                receiver.start();
            }
        }
        else
        {
            System.out.println("Invalid Arguments");
        }
    }
}
