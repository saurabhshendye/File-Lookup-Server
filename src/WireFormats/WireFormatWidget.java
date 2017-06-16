/*
 * Created by Saurabh on 6/4/2017.
 */

package WireFormats;

import endPoints.Client;
import endPoints.Server;

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

    public void requestFile() throws IOException {
        String name = new String(identifier);
        Server tempServer = Server.getInstance();
        tempServer.parseRequest(name);
    }

    public void requestResponse() throws IOException
    {
        System.out.println("Data Received");
        Client client = Client.getClientInstance();
        client.parseResponse(this.identifier);
    }

    public void errorResponse()
    {

    }
}
