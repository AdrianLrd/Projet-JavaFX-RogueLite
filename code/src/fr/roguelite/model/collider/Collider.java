package fr.roguelite.model.collider;

import fr.roguelite.model.collider.collision.Collision;
import fr.roguelite.model.entitiy.Location;
import fr.roguelite.model.entitiy.Entity;

import java.util.List;

public abstract class Collider {
    /**
     * Create a new Collider, you need to override methods because Collider is abstract
     */
    public Collider() {

    }

    /**
     * Check if an Entity can move to a given Location
     * @param entity The Entity that should move
     * @param newLocation The new Location to check
     * @return True is the Entity can mvoe or False
     */
    public abstract boolean canMove(Entity entity, Location newLocation);

    /**
     * Gets all the Collision from an Entity at the new Location
     * @param target The Entity that should move
     * @param newLocation The new Location to check
     * @return A List containing all the Collisions
     */
    public abstract List<Collision> getCollision(Entity target, Location newLocation);
}
