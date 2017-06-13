package org.engine;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Created by evan on 6/12/2017.
 */
public class sounds {

   public static void music()
   {

        try {
           File file = new File("");
           Clip clip = AudioSystem.getClip();
           clip.open(AudioSystem.getAudioInputStream(file));
           clip.start();
           Thread.sleep(clip.getMicrosecondLength());
        }
        catch (Exception e){System.err.println(e.getMessage());}


   }

}






