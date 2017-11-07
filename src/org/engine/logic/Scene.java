package org.engine.logic;

import org.objects.Player;

import java.awt.*;
import java.util.LinkedList;

/**
 * This class is the main object that holds
 * all entities that are in the game, e.g. a Level
 *
 * @author Jakub P. Szarkowicz
 */
public class Scene
{
    private static LinkedList<Entity> entities = new LinkedList<>();

    /* Loads required entities */
    public void loadEntities()
    {
        createEntity(new Player(0, 0));
    }

    /* Update all entities in the scene */
    public void update()
    {
        for(Entity entity : entities)
        {
            entity.update();
        }
    }

    /* Render all entities in the scene */
    public void render(Graphics2D g)
    {
        for(Entity entity : entities)
        {
            entity.renderTransform();
            entity.render(g);
        }
    }

    /* Spawns an entity into the scene */
    public static Entity createEntity(Entity e)
    {
        System.out.println("[I]: " + e.name);
        entities.add(e);
        return e;
    }

    /* Removed an entity from the scene */
    public static void destroyEntity(Entity e)
    {
        System.out.println("[X]: " + e.name);
        entities.remove(e);
    }

    /* Finds an entity thats in the scene */
    public static Entity findEntity(String tag)
    {
        for(Entity entity : entities)
        {
            if(entity.tag.equals(tag)) {
                return entity;
            }
        }

        // Throw error if entity is not found
        System.err.println("[E]: Could not find " +
                "entity with tag " + tag);

        return null;
    }
}
