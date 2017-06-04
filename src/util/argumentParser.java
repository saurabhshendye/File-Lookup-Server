/*
 * Created by Saurabh on 04/06/2017.
 */

package util;

import java.io.File;

public class argumentParser
{
    public int port;
    public File path;
    public String [] args;

    public argumentParser(String [] args)
    {
        this.args = args;
    }

    public boolean isValid()
    {
        if (args.length == 2)
        {
            this.port = Integer.parseInt(args[0]);
            // port needs to be positive and greater than 1023 (well known ports)
            if (port > 1023)
            {
                // here we also need to check whether the path is valid or not
                this.path = new File(args[1]);
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
