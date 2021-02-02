package fr.roguelite.model.mover;

import fr.roguelite.launch.Launch;
import fr.roguelite.model.collider.collision.Collision;
import fr.roguelite.model.entitiy.Location;
import fr.roguelite.model.Manager;
import fr.roguelite.model.attribute.Attribute;
import fr.roguelite.model.entitiy.Entity;
import fr.roguelite.model.entitiy.EntityDirection;

import java.util.List;

public class SimpleMover extends Mover {
    @Override
    public List<Collision> moveUp(Entity entity) {
        Location newLocation = entity.getLocation().clone();

        newLocation.setY(entity.getLocation().getY() - entity.getAttributes().getInt(Attribute.MOVEMENT_SPEED));

        List<Collision> collisions = Launch.getManager().getCollider().getCollision(entity, newLocation);
        if(collisions.size() == 0) {
            entity.getLocation().setY(newLocation.getY());
        }

        return collisions;
    }

    @Override
    public List<Collision> moveDown(Entity entity) {
        Location newLocation = entity.getLocation().clone();

        newLocation.setY(entity.getLocation().getY() + entity.getAttributes().getInt(Attribute.MOVEMENT_SPEED));

        List<Collision> collisions = Launch.getManager().getCollider().getCollision(entity, newLocation);
        if(collisions.size() == 0) {
            entity.getLocation().setY(newLocation.getY());
        }

        return collisions;
    }

    @Override
    public List<Collision> moveLeft(Entity entity) {
        Location newLocation = entity.getLocation().clone();

        newLocation.setX(entity.getLocation().getX() - entity.getAttributes().getInt(Attribute.MOVEMENT_SPEED));

        List<Collision> collisions = Launch.getManager().getCollider().getCollision(entity, newLocation);
        if(collisions.size() == 0) {
            entity.getSprite().setDirection(EntityDirection.LEFT);
            entity.getLocation().setX(newLocation.getX());
        }

        return collisions;
    }

    @Override
    public List<Collision> moveRight(Entity entity) {
        Location newLocation = entity.getLocation().clone();

        newLocation.setX(entity.getLocation().getX() + entity.getAttributes().getInt(Attribute.MOVEMENT_SPEED));

        List<Collision> collisions = Launch.getManager().getCollider().getCollision(entity, newLocation);

        if(collisions.size() == 0) {
            entity.getSprite().setDirection(EntityDirection.RIGHT);
            entity.getLocation().setX(newLocation.getX());
        }

        return collisions;
    }
}
