package WireFormats;

import java.io.*;

/**
 * Created by Saurabh on 6/15/2017.
 */
public class fileResponse
{
    private int type = 1;               //File found indicator
    private File absolutePath;

    public fileResponse(File file)
    {
        this.absolutePath = file;
    }

    public byte [] getByteArray() throws IOException
    {

        ByteArrayOutputStream baopstream = new ByteArrayOutputStream();
        DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(baopstream));

        RandomAccessFile rf = new RandomAccessFile(absolutePath, "r");
        byte [] b = new byte[(int) rf.length()];
        rf.readFully(b);

        int Len = b.length;
        dout.writeInt(type);
        dout.writeInt(Len);
        dout.write(b);
        dout.flush();

        byte[] marshaled = baopstream.toByteArray();

        baopstream.close();
        dout.close();

        return marshaled;

    }
}
