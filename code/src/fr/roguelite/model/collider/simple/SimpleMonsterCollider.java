package fr.roguelite.model.collider.simple;

import fr.roguelite.launch.Launch;
import fr.roguelite.model.collider.Collider;
import fr.roguelite.model.collider.collision.Collision;
import fr.roguelite.model.collider.collision.EntityCollision;
import fr.roguelite.model.collider.collision.WallCollision;
import fr.roguelite.model.entitiy.BoundingBox;
import fr.roguelite.model.entitiy.Entity;
import fr.roguelite.model.entitiy.Location;
import fr.roguelite.model.entitiy.entities.Arrow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleMonsterCollider extends Collider {
    @Override
    public boolean canMove(Entity target, Location newLocation) {
        boolean collision = false;

        BoundingBox actualBoundingBox = target.getBoundingBox();
        BoundingBox box = new BoundingBox(newLocation, actualBoundingBox.getOffsetx(), actualBoundingBox.getOffsety(), actualBoundingBox.getDx(), actualBoundingBox.getDy());

        for(Entity entity :
                Launch.getManager().getGame().getEntityContainer().getEntities()
                        .stream()
                        .filter(entity -> entity != target)
                        .collect(Collectors.toList())
        )
        {
            if (entity.getBoundingBox().contains(box)) {
                collision = true;
                break;
            }
        }

        BoundingBox feets = box.clone();
        feets.setOffsety(feets.getOffsety() + feets.getDy() + 2);
        feets.setDy(20);

        BoundingBox area = new BoundingBox(new Location(30, 120), new Location(1250, 590));
        if(feets.collide(area)) collision = true;

        return !collision;
    }

    @Override
    public List<Collision> getCollision(Entity target, Location newLocation) {
        List<Collision> collisions = new ArrayList<>();

        BoundingBox actualBoundingBox = target.getBoundingBox();
        BoundingBox box = new BoundingBox(newLocation, actualBoundingBox.getOffsetx(), actualBoundingBox.getOffsety(), actualBoundingBox.getDx(), actualBoundingBox.getDy());

        for(Entity entity :
                Launch.getManager().getGame().getEntityContainer().getEntities()
                        .stream()
                        .filter(entity -> entity != target)
                        .collect(Collectors.toList())
        )
        {
            if (entity.getBoundingBox().contains(box)) {
                collisions.add(new EntityCollision(entity));
            }
        }

        BoundingBox feets = box.clone();
        feets.setOffsety(feets.getOffsety() + feets.getDy() + 2);
        feets.setDy(20);

        BoundingBox area = new BoundingBox(new Location(30, 120), new Location(1250, 590));
        if(feets.collide(area)) collisions.add(new WallCollision());

        return collisions;
    }
}
