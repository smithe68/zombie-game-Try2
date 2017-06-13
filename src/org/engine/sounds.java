package org.engine;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Executable;

/**
 * Created by evan on 6/12/2017.
 */
public class sounds {

   public static void music()
   {

        try {
           File file = new File("/org/resources/sounds/backgroundMusic.mp3");
           Clip clip = AudioSystem.getClip();
           clip.open(AudioSystem.getAudioInputStream(file));
           clip.start();
           Thread.sleep(clip.getMicrosecondLength());
        }
        catch (Exception e){System.err.println(e.getMessage());}


   }

}






