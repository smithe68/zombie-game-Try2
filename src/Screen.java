/**
 * Created by evan on 6/8/2017.
 */
import javax.swing.*;
import java.awt.*;

public class Screen extends Canvas

{
    public Screen (int width, int height,String title, Main main){
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(main);
        frame.setVisable(true);
        main.start;
    }
}
