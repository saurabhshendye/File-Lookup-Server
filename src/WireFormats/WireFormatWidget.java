/*
 * Created by Saurabh on 6/4/2017.
 */

package WireFormats;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class WireFormatWidget
{
    private int type;
    private byte [] identifier;


    public WireFormatWidget(byte [] bytes) throws IOException
    {
        ByteArrayInputStream baInputStream = new ByteArrayInputStream(bytes);
        DataInputStream din = new DataInputStream(new BufferedInputStream(baInputStream));

        this.type = din.readInt();
        int Len = din.readInt();
        byte[] data = new byte[Len];

        din.readFully(data);
        this.identifier = data;

        din.close();
        baInputStream.close();
    }

    public int getType()
    {
        return type;
    }
}
