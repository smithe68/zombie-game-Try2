package org.engine;

import javax.sound.sampled.*;
import java.net.URL;

/**
 * Created by evan on 6/12/2017.
 */
public class Sound {

   public static void PlaySound(String path, float volume, boolean loop)
   {
       int loopInt = 0;

       if(loop)
       {
           loopInt = -1;
       }
       else
       {
           loopInt = 0;
       }

        try
        {
            URL url = Clip.class.getResource(path);
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(url);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);

            clip.start();
            clip.loop(loopInt);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
   }

}






