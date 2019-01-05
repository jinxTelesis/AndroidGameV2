package app.calcounter.com.individualproject3;

import android.media.MediaPlayer;

import java.io.File;

public class AudioClass {

    /** AudioClass starts background music of file located
     *  In the resources of this project
     */

    /**
     *
     * @param path files path for mediaplayer
     * @param fileName files name mediaplayer
     */

    public void audioPlayer(String path, String fileName)
    {
        MediaPlayer mp = new MediaPlayer();

        try{
            mp.setDataSource(path + File.separator + fileName);
            mp.prepare();
            mp.start();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
