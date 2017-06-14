package org.engine;

import org.input.Input;
import org.world.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.IOException;

public class Renderer
{
    private static Frame frame;
    private static Canvas canvas;

    private static int canvasWidth = 0;
    private static int canvasHeight = 0;

    private static final int GAME_WIDTH = 400;
    private static final int GAME_HEIGHT = 250;

    public static int gameWidth = 0;
    public static int gameHeight = 0;

    private static long lastFpsCheck = 0;
    private static int currentFPS = 0;
    private static int totalFrames = 0;

    private static int targetFPS = 60;
    private static int targetTime = 1000000000 / targetFPS;

    public static float camX = 0;
    public static float camY = 0;

    public static float camPosX = 0;
    public static float camPosY = 0;

    // Find Size of Screen and Scale to it
    private static void GetBestSize()
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        Dimension screenSize = toolkit.getScreenSize();

        boolean done = false;

        while(!done)
        {
            canvasWidth += GAME_WIDTH;
            canvasHeight += GAME_HEIGHT;

            if(canvasWidth > screenSize.width || canvasHeight > screenSize.height)
            {
                canvasWidth -= GAME_WIDTH;
                canvasHeight -= GAME_HEIGHT;
                done = true;
            }
        }

        int xDiff = screenSize.width - canvasWidth;
        int yDiff = screenSize.height - canvasHeight;
        int factor = canvasWidth / GAME_WIDTH;

        gameWidth = canvasWidth / factor + xDiff / factor;
        gameHeight = canvasHeight / factor + yDiff / factor;

        canvasWidth = gameWidth * factor;
        canvasHeight = gameHeight * factor;
    }

    private static void MakeFullscreen()
    {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = env.getDefaultScreenDevice();

        if(gd.isFullScreenSupported())
        {
            frame.setUndecorated(true);
            gd.setFullScreenWindow(frame);
        }
    }

    public static void init()
    {
        GetBestSize();

        frame = new Frame();
        canvas = new Canvas();

        canvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));

        frame.add(canvas);

        //MakeFullscreen();

        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                Game.quit();
            }
        });

        frame.setVisible(true);

        canvas.addKeyListener(new Input());

        StartRendering();
    }

    private static void StartRendering()
    {
        Thread thread = new Thread(() ->
    {
        GraphicsConfiguration gc = canvas.getGraphicsConfiguration();
        VolatileImage vImage = gc.createCompatibleVolatileImage(gameWidth, gameHeight);

        while(true)
        {
            long startTime = System.nanoTime();

            // FPS Counter
            totalFrames++;
            if(System.nanoTime() > lastFpsCheck + 1000000000)
            {
                lastFpsCheck = System.nanoTime();
                currentFPS = totalFrames;
                totalFrames = 0;
            }

            if(vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE)
            {
                vImage = gc.createCompatibleVolatileImage(gameWidth, gameHeight);
            }

            Graphics g = vImage.getGraphics();

            // Clear the Screen
            g.setColor(Color.black);
            g.fillRect(0, 0, gameWidth, gameHeight);

            // Update Stuff
            World.update();
            Input.FinishInput();

            // Render Stuff
            World.render(g);

            // Draw FPS Counter
            g.setColor(Color.LIGHT_GRAY);
            g.drawString(String.valueOf(currentFPS), 2, gameHeight - 10);

            g.dispose();

            g = canvas.getGraphics();
            g.drawImage(vImage, 0, 0, canvasWidth, canvasHeight, null);

            g.dispose();

            long totalTime = System.nanoTime() - startTime;

            if(totalTime < targetTime)
            {
                try
                {
                    Thread.sleep((targetTime - totalTime) / 1000000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    });

        thread.setName("Rendering Thread");
        thread.start();
    }

    public static BufferedImage LoadImage(String path) throws IOException
    {
        BufferedImage rawImage = ImageIO.read(Renderer.class.getResource(path));
        BufferedImage finalImage = canvas.getGraphicsConfiguration()
                .createCompatibleImage(rawImage.getWidth(), rawImage.getHeight(), rawImage.getTransparency());

        finalImage.getGraphics().drawImage(rawImage, 0, 0, rawImage.getWidth(), rawImage.getHeight(), null);

        return finalImage;
    }
}
