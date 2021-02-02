package fr.roguelite.model.collider.collision;

import fr.roguelite.model.entitiy.Entity;

public class EntityCollision implements Collision {
    /**
     * The entity that is colliding
     */
    private Entity entity;

    /**
     * Create a new EntityCollision
     * @param entity The Entity that Collide
     */
    public EntityCollision(Entity entity) {
        this.entity = entity;
    }

    /**
     * Gets the Entity that Collide
     */
    public Entity getEntity() {
        return entity;
    }
}
