/*
 * Created by Saurabh on 04/06/2017.
 */

package Transport;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPReceiver extends Thread
{
    private Socket S;
    private DataInputStream din;

    public TCPReceiver(Socket S) throws IOException
    {
        this.S = S;
        this.din = new DataInputStream(S.getInputStream());
    }

    public void run()
    {

    }
}
