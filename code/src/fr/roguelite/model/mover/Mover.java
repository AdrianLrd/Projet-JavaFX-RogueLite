package fr.roguelite.model.mover;

import fr.roguelite.model.collider.collision.Collision;
import fr.roguelite.model.Manager;
import fr.roguelite.model.entitiy.Entity;

import java.util.List;

public abstract class Mover {
    /**
     * Creates a new Mover
     */
    public Mover() {

    }

    /**
     * Try to move up the Entity
     * @param entity The Entity that needs to move Up
     * @return All the collision if the entity can't move
     */
    public abstract List<Collision> moveUp(Entity entity);

    /**
     * Try to move up the Entity
     * @param entity The Entity that needs to move Down
     * @return All the collision if the entity can't move
     */
    public abstract List<Collision> moveDown(Entity entity);

    /**
     * Try to move up the Entity
     * @param entity The Entity that needs to move Left
     * @return All the collision if the entity can't move
     */
    public abstract List<Collision> moveLeft(Entity entity);

    /**
     * Try to move up the Entity
     * @param entity The Entity that needs to move Right
     * @return All the collision if the entity can't move
     */
    public abstract List<Collision> moveRight(Entity entity);
}
