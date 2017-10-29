package org.engine.logic;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound
{
    // Player any Sound in Project or URL
    public static void playSound(String path, float volume, boolean loop)
    {
        int loopInt;

        if(loop) {loopInt = -1;}

        else {loopInt = 0;}

        try
        {
            // Get the Sound Clip
            URL url = Clip.class.getResource(path);
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            // Process Sound Clip
            stream = AudioSystem.getAudioInputStream(url);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);

            // Control Volume
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);

            // Player Sound Clip
            clip.start();
            clip.loop(loopInt);
        }
        catch (Exception e) {System.err.println(e.getMessage());}
    }
}






