package app.calcounter.com.individualproject3;

import android.media.MediaPlayer;

import java.io.File;

public class AudioClass {

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
