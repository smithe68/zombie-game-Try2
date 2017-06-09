import javax.swing.*;

public class Button
{
    public static void CreateButton(String text, int sizeX, int sizeY, float posX, float posY)
    {
        JButton button = new JButton(text);

        // Set Position and Size of Button
        button.setSize(sizeX, sizeY);
        button.setAlignmentX(posX);
        button.setAlignmentY(posY);
        // Evan if you see this then it works!
    }
}
