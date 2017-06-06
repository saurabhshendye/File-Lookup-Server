/*
 * Created by Saurabh on 03/06/2017.
 */

package endPoints;

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

        }
    }
}
