/*
 * Created by Saurabh on 6/4/2017.
 */

package util;

import java.io.File;

public class findFile
{
    private File path;
    private static final findFile ff = new findFile();

    private findFile()
    {

    }

    public void setPath(File path)
    {
        this.path = path;
    }
    public static findFile getInstance()
    {
        return ff;
    }
}
