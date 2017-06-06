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
        clientArgParser parser = new clientArgParser(args);
        if (parser.isValid())
        {
            Socket clientSocket = new Socket(parser.hostName, parser.port);
            TCPSender sender  = new TCPSender(clientSocket);

            fileRequest request = new fileRequest(parser.fileName);
            byte [] WfRequest = request.getByteArray();

            sender.send_and_maintain(WfRequest);

            TCPReceiver receiver = new TCPReceiver(clientSocket);
            receiver.start();
        }
    }
}
