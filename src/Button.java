import javax.swing.*;

public class Button
{
    public static void CreateButton(int sizeX, int sizeY, float posX, float posY)
    {
        JButton button = new JButton("Main Button");

        // Set Position and Size of Button
        button.setSize(sizeX, sizeY);
        button.setAlignmentX(posX);
        button.setAlignmentY(posY);
    }
}
