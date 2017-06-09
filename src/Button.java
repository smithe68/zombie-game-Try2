import javax.swing.*;

public class Button
{
    public string buttonName;

    public static void CreateButton(int sizeX, int sizeY)
    {
        JButton button = new JButton("Main Button");
        button.setSize(sizeX, sizeY);
    }
}
