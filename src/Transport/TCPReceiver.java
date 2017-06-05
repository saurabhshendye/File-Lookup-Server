/*
 * Created by Saurabh on 04/06/2017.
 */

package Transport;

import WireFormats.WireFormatWidget;

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
        while (S != null)
        {
            try
            {
                int dLen = din.readInt();
                byte[] data = new byte[dLen];
                din.readFully(data);
                WireFormatWidget WireFormat = new WireFormatWidget(data);
                int type = WireFormat.getType();
//                switch (type)
//                {
//                    case 0: WireFormat.register();                      // 0 for File Request
//                        break;
//                    default: System.out.println("Unknown Message");
//                        break;
//                }
            }
            catch (IOException e1)
            {
                System.out.println("Error Message: " +e1.getMessage());
                break;
            }
//            catch (InterruptedException e)
//            {
//                e.printStackTrace();
//                break;
//            }
        }
    }
}
