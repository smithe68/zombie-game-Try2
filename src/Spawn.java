public class Spawn
{
    private Handler handler;
    private HUD hud;

    private boolean spawnEnemy = true;

    public Spawn(Handler handler, HUD hud)
    {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick()
    {
        if(spawnEnemy)
        {
            handler.AddObject(new BasicZombie(200, 200, ID.BasicZombie));
            spawnEnemy = false;
        }
    }
}
