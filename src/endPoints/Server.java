/*
 * Created by Saurabh on 03/06/2017.
 */

package endPoints;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable
{
    private int port;
    private File path;
    private ServerSocket serverSocket;

    private Server(int port, File path) throws IOException
    {
        this.port = port;
        this.path = path;
        this.serverSocket = new ServerSocket(port,5);
    }

    public void run()
    {

    }

    public static void main(String [] args)
    {
        //Server server = new Server();
    }
}
