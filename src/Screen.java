import javax.swing.*;
import java.awt.*;

public class Screen extends Canvas
{
    public Screen (int width, int height,String title, Main main)
    {
        JFrame frame = new JFrame(title);

        // Set Frame Size
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));

        // Set Frame Settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(main);

        main.start();
    }
}
