package org.engine.components;

import org.engine.input.Input;
import org.engine.logic.Component;
import org.engine.logic.Entity;

public class Player extends Component
{
    private SpriteRenderer renderer;

    public Player(Entity parent) {
        super(parent);
    }

    @Override
    public void start()
    {
        renderer = (SpriteRenderer)parent.addComponent(new SpriteRenderer(parent));
        renderer.sprite = SpriteRenderer.getImageFromFile("Player-FaceDown.png");
        renderer.setSize(parent.width, parent.height);
    }

    @Override
    public void update()
    {
        parent.x += Input.getInputAxis("Horizontal") * 2;
        parent.y += Input.getInputAxis("Vertical") * 2;
    }
}
