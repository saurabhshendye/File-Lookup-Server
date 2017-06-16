/*
 * Created by Saurabh on 6/6/2017.
 */

package util;

public class clientArgParser
{
    public int port;
//    public File path;
    public String path;
    public String fileName;
    public String hostName;
    public String [] args;

    public clientArgParser(String [] args)
    {
        this.args = args;
    }

    public boolean isValid()
    {
        if (args.length == 4)
        {
            this.port = Integer.parseInt(args[1]);
            // port needs to be positive and greater than 1023 (well known ports)
            if (port > 1023)
            {
                // here we also need to check whether the path is valid or not
//                this.path = new File(args[3]);
                this.path = args[3];
                this.fileName = args[2];
                this.hostName = args[0];
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

}
