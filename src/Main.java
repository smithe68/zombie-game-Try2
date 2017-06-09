import javax.swing.*;

public class Main
{
    public static JFrame mainScreen;
    public static JButton mainButton;

    public static void main(String[] args)
    {
        mainScreen = Screen.CreateScreen();
        mainButton = Button.CreateButton("Click Me!");

        mainScreen.add(mainButton);
    }
}
