/*
 * Created by Saurabh on 6/4/2017.
 */

package WireFormats;

import util.argumentParser;
import util.findFile;

import java.io.*;

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

    public void requestFile() throws IOException {
        String name = new String(identifier);
        findFile ff = new findFile();
        ff.setPath(argumentParser.getPath());
        ff.fileLookup(name, ff.getPath());
        if(ff.isPresent())
        {
            RandomAccessFile rf = new RandomAccessFile(ff.getPath(), "r");
            byte [] b = new byte[(int) rf.length()];
            rf.readFully(b);
            System.out.println("Byte Array Created");
        }
        else
        {

        }

    }
}
