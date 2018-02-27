package org.engine.logic;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;

/**
 * This class is the main object that holds
 * all entities that are in the game, e.g. a Level
 * @author Jakub P. Szarkowicz
 */
public class Scene
{
    public String sceneName;

    public LinkedList<Entity> entities = new LinkedList<>();

    public Scene(String name) {
        this.sceneName = name;
    }

    /* Loads required entities */
    public void loadEntities()
    {

    }

    /* Update all entities in the scene */
    public void update()
    {
        for(int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
    }

    /* Render all entities in the scene */
    public void render(Graphics2D g)
    {
        for(int i = 0; i < entities.size(); i++)
        {
            entities.get(i).renderTransform();
            entities.get(i).render(g);
        }
    }

    /* Spawns an entity into the scene */
    public Entity createEntity(Entity e)
    {
        System.out.println("[I]: " + e.name);
        entities.add(e);
        Collections.sort(entities);
        return e;
    }

    /* Removed an entity from the scene */
    public void destroyEntity(Entity e)
    {
        System.out.println("[X]: " + e.name);
        entities.remove(e);
    }

    /* Finds an entity thats in the scene */
    public Entity findEntity(String tag)
    {
        for(Entity entity : entities)
        {
            if(entity.tag.equals(tag)) {
                return entity;
            }
        }

        // Throw error if entity is not found
        System.err.println("[ERROR]: Could not find " +
                "entity with tag " + tag);

        return null;
    }
}
