import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable
{
    public static final int screenWidth = 1920;
    public static final int screenHeight = screenWidth / 12 * 9;

    private Thread thread;
    private boolean running = false;

    private Handler handler;

    public Main()
    {

        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Screen(screenWidth, screenHeight, "Clicker Game", this);

        handler.AddObject(new Player(100, 100, ID.Player));
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

        g.setColor(Color.black);
        g.fillRect(0, 0, screenWidth, screenHeight);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args)
    {
        new Main();
    }
}
