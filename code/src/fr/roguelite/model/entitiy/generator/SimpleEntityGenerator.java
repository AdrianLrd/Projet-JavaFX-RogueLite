package fr.roguelite.model.entitiy.generator;

import fr.roguelite.launch.Launch;
import fr.roguelite.model.entitiy.BoundingBox;
import fr.roguelite.model.entitiy.Location;
import fr.roguelite.model.Manager;
import fr.roguelite.model.entitiy.Entity;
import fr.roguelite.model.entitiy.EntityType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleEntityGenerator extends EntityGenerator{
    @Override
    public Entity generate(EntityType type) {
        try {
            Constructor constructor = type.getEntityClass().getDeclaredConstructor();
            Entity entity = (Entity) constructor.newInstance();

            BoundingBox area = new BoundingBox(new Location(30, 120), new Location(1250, 570));

            Location loc = entity.getLocation().clone();

            int essais = 0;
            boolean success;

            do {
                int x = (int) (
                        Math.random() * (area.getBottomRight().getX()
                                - area.getTopLeft().getX()
                                - entity.getBoundingBox().getDy())
                                + area.getTopLeft().getX()
                );

                int y = (int) (
                        Math.random() * (area.getBottomRight().getY()
                                - area.getTopLeft().getY()
                                - entity.getBoundingBox().getDy())
                                + area.getTopLeft().getY()
                );

                loc.setX(x);
                loc.setY(y);

                success = Launch.getManager().getCollider().canMove(entity, loc);
                essais++;
            }
            while (!success && essais < 10);

            if(success) {
                entity.getLocation().setX(loc.getX());
                entity.getLocation().setY(loc.getY());

                return entity;
            }

            return null;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
