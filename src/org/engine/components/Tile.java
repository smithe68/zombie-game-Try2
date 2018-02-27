package org.engine.components;

import org.engine.logic.Component;
import org.engine.logic.Entity;

public class Tile extends Component
{
    private SpriteRenderer renderer;

    public Tile(Entity parent) {
        super(parent);
    }

    @Override
    public void start()
    {
        renderer = (SpriteRenderer)parent.addComponent(new SpriteRenderer(parent));
        renderer.sprite = SpriteRenderer.getImageFromFile("Dev.png");
        renderer.setSize(32, 32);
    }

    @Override
    public void update() {

    }
}
