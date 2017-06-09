/**
 * Created by evan on 6/8/2017.
 */
import javax.swing.*;
import java.awt.*;

public class Screen extends Canvas
{
    public static JFrame CreateScreen()
    {
        JFrame mainScreen = new JFrame();

        mainScreen.setSize(1920,1080);
        mainScreen.setVisible(true);
        mainScreen.setTitle("Game Window");
        mainScreen.setResizable(false);
        mainScreen.setLocationRelativeTo(null);

        return mainScreen;
    }
}
