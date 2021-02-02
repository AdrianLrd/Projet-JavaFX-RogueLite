package fr.roguelite.model.entitiy.entities;

import fr.roguelite.model.entitiy.Animation;
import fr.roguelite.model.entitiy.BoundingBox;
import fr.roguelite.model.attribute.Attribute;
import fr.roguelite.model.entitiy.Entity;

import java.util.Arrays;

public class Player extends Entity {
    int scaleFactor = 5;

    public Player(String texturePath) {
        super(texturePath);

        attributes.set(Attribute.ATTACK_SPEED, 3);
        attributes.set(Attribute.DAMAGE_POINT, 3);
        attributes.set(Attribute.MOVEMENT_SPEED, 10);
        attributes.set(Attribute.HEALTH_POINT, 20);

        sprite.setAnimations(Arrays.asList(Animation.IDLE, Animation.WALKING));
        sprite.setScaleFactor(scaleFactor);

        boundingBox = new BoundingBox(location, 4 * scaleFactor, 10 * scaleFactor, 9 * scaleFactor, 18 * scaleFactor);
    }
}
