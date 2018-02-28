package org.engine.components;

import org.engine.input.Input;
import org.engine.logic.Component;
import org.engine.logic.Entity;

import java.awt.image.BufferedImage;

public class Player extends Component
{
    private SpriteRenderer renderer;

    private BufferedImage[] walkCycle;

    public Player(Entity parent) {
        super(parent);
    }

    @Override
    public void start()
    {
        BufferedImage walk = SpriteRenderer.getImage("Player-Sheet.png");
        walkCycle = SpriteRenderer.splitSheet(walk, 0, 0, 11, 25, 3);

        parent.width = 22;
        parent.height = 50;

        renderer = (SpriteRenderer)parent.addComponent(new SpriteRenderer(parent));
        renderer.setSize(parent.width, parent.height);
    }

    @Override
    public void update()
    {
        double hori = Input.getInputAxis("Horizontal");
        double vert = Input.getInputAxis("Vertical");

        parent.x += hori * 2;
        parent.y += vert * 2;

        if(hori != 0 | vert != 0) {
            renderer.sprite = renderer.animateSheet(walkCycle, 5);
        } else { renderer.sprite = walkCycle[0]; }
    }
}
