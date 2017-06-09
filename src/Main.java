import javax.swing.*;
import java.awt.*;

public class Main extends Canvas
{
    public static JFrame mainScreen;
    public static JButton mainButton;

    public static void main(String[] args)
    {
        JPanel mainPanel = new JPanel();
        JPanel scorePanel = new JPanel();

        mainPanel.setSize(new Dimension(400, 300));
        mainPanel.setLayout(new BorderLayout());

        mainScreen = Screen.CreateScreen();
        mainButton = Button.CreateButton("Click Me!");
        mainButton.setPreferredSize(mainPanel.getSize());

        mainScreen.add(mainPanel);

        mainPanel.add(mainButton);
    }
}
