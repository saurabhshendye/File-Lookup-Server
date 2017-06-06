/*
 * Created by Saurabh on 04/06/2017.
 */

package WireFormats;


import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class fileRequest
{
    private String name;
    private int type = 0;

    public fileRequest(String fileName)
    {
        this.name = fileName;
    }

    public byte[] getByteArray() throws IOException
    {
        // This method creates a byte array which needs to be written onto the socket
        ByteArrayOutputStream baopstream = new ByteArrayOutputStream();
        DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(baopstream));

        byte[] array = this.name.getBytes();
        int Len = array.length;

        dout.writeInt(type);
        dout.writeInt(Len);
        dout.write(array);
        dout.flush();

        byte[] marshaled = baopstream.toByteArray();

        baopstream.close();
        dout.close();

        return marshaled;
    }
}
