import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends Canvas
{
    public static JFrame mainScreen;
    public static JButton mainButton;

    public static void main(String[] args)
    {
        JPanel mainPanel = new JPanel();
        JPanel scorePanel = new JPanel();

        mainPanel.setSize(new Dimension(400, 300));

        mainScreen = Screen.CreateScreen();
        mainButton = Button.CreateButton("Click Me!");
        mainScoreScreen = ScoreScreen.CreateScoreScreen
        mainButton.setPreferredSize(mainPanel.getSize());



        mainScreen.add(mainPanel);

        mainPanel.add(mainButton);
    }
}
