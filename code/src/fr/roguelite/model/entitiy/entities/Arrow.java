package fr.roguelite.model.entitiy.entities;

import fr.roguelite.model.entitiy.BoundingBox;
import fr.roguelite.model.attribute.Attribute;
import fr.roguelite.model.entitiy.Entity;
import fr.roguelite.model.entitiy.EntityDirection;

public class Arrow extends Entity {
    static String texturePath = "resources/texture/entity/arrow.png";
    int scaleFactor = 4;

    private Entity shooter;

    public Arrow(){
        super(texturePath);
        attributes.set(Attribute.ATTACK_SPEED, 8);
        attributes.set(Attribute.DAMAGE_POINT, 3);
        attributes.set(Attribute.MOVEMENT_SPEED, 15);
        attributes.set(Attribute.HEALTH_POINT, 90);

        sprite.setDirection(EntityDirection.RIGHT);
        sprite.setScaleFactor(scaleFactor);

        boundingBox = new BoundingBox(location, 0 * scaleFactor, 0 * scaleFactor, 17 * scaleFactor, 7 * scaleFactor);

    }

    public Entity getShooter() {
        return shooter;
    }

    public void setShooter(Entity shooter) {
        this.shooter = shooter;
    }
}
