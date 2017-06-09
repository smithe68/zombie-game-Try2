import javax.swing.*;
import java.awt.*;

public class Main
{
    public static JFrame mainScreen;
    public static JButton mainButton;

    public static void main(String[] args)
    {
        JPanel mainPanel;

        mainPanel = new JPanel();

        mainPanel.setSize(new Dimension(400, 300));

        mainScreen = Screen.CreateScreen();
        mainButton = Button.CreateButton("Click Me!");
        mainButton.setPreferredSize(mainPanel.getSize());

        mainScreen.add(mainPanel);

        mainPanel.add(mainButton);
    }
}
