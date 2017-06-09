import javax.swing.*;

public class Main
{
    public static JFrame mainScreen;
    public static JButton mainButton;

    public static void main(String[] args)
    {
        mainScreen = Screen.CreateScreen();
        mainButton = Button.CreateButton("Click Me!", 200, 200, 100, 100);

        mainScreen.add(mainButton);
    }
}
