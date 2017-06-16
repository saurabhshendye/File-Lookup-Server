/*
 * Created by Saurabh on 03/06/2017.
 */

package endPoints;

import Transport.TCPReceiver;
import Transport.TCPSender;
import WireFormats.fileRequest;
import util.clientArgParser;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Socket;

public class Client
{
    private static final Client client = new Client();
    private static String name;
    private static String path;

    public static Client getClientInstance()
    {
        return client;
    }

    public static void main(String [] args) throws IOException
    {
        // Client Argument Parser to check validity of the parser
        clientArgParser parser = new clientArgParser(args);
        if (parser.isValid())
        {
            // From parser getting the name of the file to be requested
            // and location to store the file
            name = parser.fileName;
            path = parser.path;

            // Creating Client Socket and TCPSender
            Socket clientSocket = new Socket(parser.hostName, parser.port);
            TCPSender sender  = new TCPSender(clientSocket);

            // Creating byte array of the request using Wireformats
            fileRequest request = new fileRequest(parser.fileName);
            byte [] WfRequest = request.getByteArray();

            // Sending the request to the server
            sender.send_and_maintain(WfRequest);
            System.out.println("Request Sent");

            // Starting the Receiver Thread
            TCPReceiver receiver = new TCPReceiver(clientSocket);
            receiver.start();

        }
    }

    public void parseResponse(byte [] byteArray) throws IOException
    {
        String fileName = path + "/" + name;
        RandomAccessFile wf = new RandomAccessFile(fileName, "rw");
//            FileOutputStream fos = new FileOutputStream(name);
//        String content = "writing to a file";
        wf.write(byteArray);
        System.out.println("File Written");
    }
}
