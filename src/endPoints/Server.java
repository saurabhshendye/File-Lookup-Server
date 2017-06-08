/*
 * Created by Saurabh on 03/06/2017.
 */

package endPoints;

import Transport.TCPReceiver;
import util.argumentParser;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private int port;
    private ServerSocket serverSocket;

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
            Server server = new Server(argParse.port);
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
            }
        }
        else
        {
            System.out.println("Invalid Arguments");
        }
    }
}
