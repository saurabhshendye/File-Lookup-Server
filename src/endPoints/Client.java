/*
 * Created by Saurabh on 03/06/2017.
 */

package endPoints;

import Transport.TCPReceiver;
import Transport.TCPSender;
import WireFormats.fileRequest;
import util.clientArgParser;

import java.io.IOException;
import java.net.Socket;

public class Client
{
    public static void main(String [] args) throws IOException
    {
        // Client Argument Parser to check validity of the parser
        clientArgParser parser = new clientArgParser(args);
        if (parser.isValid())
        {
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
}
