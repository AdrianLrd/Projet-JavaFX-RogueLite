package fr.roguelite.model.entitiy.entities;

import fr.roguelite.model.attribute.Attribute;
import fr.roguelite.model.entitiy.Animation;
import fr.roguelite.model.entitiy.BoundingBox;
import fr.roguelite.model.entitiy.Entity;
import fr.roguelite.model.entitiy.Monster;

import java.util.Arrays;

public class Worc extends Monster {

    static String texturePath = "resources/texture/entity/monster/orc_warrior.png";
    int scaleFactor = (int) (Math.random() * 2 + 5);

    public Worc() {
        super(texturePath);
        attributes.set(Attribute.ATTACK_SPEED, 8);
        attributes.set(Attribute.DAMAGE_POINT, 30);
        attributes.set(Attribute.MOVEMENT_SPEED, 1);
        attributes.set(Attribute.HEALTH_POINT, 5);

        sprite.setAnimations(Arrays.asList(Animation.IDLE, Animation.WALKING));
        sprite.setScaleFactor(scaleFactor);

        boundingBox = new BoundingBox(location, 3 * scaleFactor, 5 * scaleFactor, 10 * scaleFactor, 15 * scaleFactor);
    }
}
