package org.engine;

import org.input.KeyInput;
import org.objects.*;
import org.ui.HUD;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable
{
    public static final int screenWidth = 800;
    public static final int screenHeight = screenWidth / 12 * 9;

    public static double deltaTime;

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private HUD hud;

    private LevelGeneration levelGen;

    // Special GameObject
    private GameObject player;

    public Main()
    {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Screen(screenWidth, screenHeight, "Clicker Game", this);

        hud = new HUD(handler);

        levelGen = new LevelGeneration(handler);
        levelGen.CreateLevel();

        // Spawn the Player
        player = handler.AddObject(new Player(100, 100, ID.Player, handler));
        handler.AddObject(new BasicZombie(200, 200, ID.BasicZombie, player, handler));
        handler.AddObject(new Follower(200, 200, ID.Follower, player, handler));
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
    public static float clamp(float var, float min,float max)
    {
        if(var>= max)
        {
            return var = max;
        }
        else if (var <= min)
        {
            return var = min;
        }
        else
        {
            return var;
        }
    }

    public static void main(String[] args)
    {
        new Main();
    }
}
