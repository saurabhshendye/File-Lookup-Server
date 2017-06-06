/*
 * Created by Saurabh on 6/4/2017.
 */

package util;

import java.io.File;

public class findFile
{
    private File path = null;
    private static final findFile ff = new findFile();
    private static boolean flag = false;
    private static File P;

    private findFile()
    {

    }

    public synchronized void fileLookup(String name, File file)
    {
        File[] list = file.listFiles();
        if(list!=null)
            for (File fil : list)
            {
                if (fil.isDirectory())
                {
                    fileLookup(name, fil);
                }
                else if (name.equalsIgnoreCase(fil.getName()))
                {
                    P = fil.getParentFile();
                    flag = true;
                }
            }
    }

    public void setPath(File path)
    {
        this.path = path;
    }

    public File getPath()
    {
        return this.path;
    }
    public static findFile getInstance()
    {
        return ff;
    }
}
