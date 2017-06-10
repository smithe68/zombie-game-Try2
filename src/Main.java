import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.logging.Level;

public class Main extends Canvas implements Runnable
{
    public static final int screenWidth = 800;
    public static final int screenHeight = screenWidth / 12 * 9;

    public static double deltaTime;

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private HUD hud;
    private Spawn spawn;
    private BufferedImageLoader imageLoader;
    private LevelGeneration levelGen;

    // Special GameObject
    private GameObject player;

    // Sprites
    private BufferedImage playerImage;
    private BufferedImage zombieImage;
    private BufferedImage stoneTile;

    public Main()
    {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Screen(screenWidth, screenHeight, "Clicker Game", this);

        hud = new HUD(handler);

        //spawn = new Spawn(handler, hud, zombieImage);

        imageLoader = new BufferedImageLoader();

        init();

        levelGen = new LevelGeneration(handler, stoneTile);
        levelGen.CreateLevel();

        // Spawn the Player
        player = handler.AddObject(new Player(100, 100, ID.Player, handler, playerImage));
        handler.AddObject(new BasicZombie(200, 200, ID.BasicZombie, zombieImage, player));
    }

    public void init()
    {
        try
        {
            playerImage = imageLoader.loadImage("/PlayerDude.png");
            zombieImage = imageLoader.loadImage("/Zombie.png");
            stoneTile = imageLoader.loadImage("/GrassTile.png");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        }
        catch(Exception e) {}
    }

    public void run()
    {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running)
        {
            long now = System.nanoTime();

            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1)
            {
                tick();
                delta--;
            }

            if(running)
            {
                render();
            }

            deltaTime = delta;

            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;

                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }

        stop();
    }

    private void tick()
    {
        handler.tick();
        hud.tick();
        //spawn.tick();
    }

    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Color field = new Color (142,111,58);
        g.setColor(field);
        g.fillRect(0, 0, screenWidth, screenHeight);

        handler.render(g);

        hud.render(g);

        g.dispose();
        bs.show();
    }
    public static int clamp(int var, int min,int max)
    {
        if(var>= max)
                return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }

    public static void main(String[] args)
    {
        new Main();
    }
}
