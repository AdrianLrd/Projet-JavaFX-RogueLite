package fr.roguelite.model.collider;

import fr.roguelite.model.collider.collision.Collision;
import fr.roguelite.model.entitiy.Location;
import fr.roguelite.model.collider.simple.SimpleArrowCollider;
import fr.roguelite.model.collider.simple.SimpleMonsterCollider;
import fr.roguelite.model.collider.simple.SimplePlayerCollider;
import fr.roguelite.model.entitiy.Entity;
import fr.roguelite.model.entitiy.Monster;
import fr.roguelite.model.entitiy.entities.Arrow;
import fr.roguelite.model.entitiy.entities.Player;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class SimpleCollider extends Collider {
    private List<Pair<Class, Collider>> colliders = new ArrayList<>();

    public SimpleCollider() {
        colliders.add(new Pair<>(Arrow.class, new SimpleArrowCollider()));
        colliders.add(new Pair<>(Player.class, new SimplePlayerCollider()));
        colliders.add(new Pair<>(Monster.class, new SimpleMonsterCollider()));
    }

    @Override
    public boolean canMove(Entity target, Location newLocation) {
        Collider collider = null;
        for(Pair type : colliders) {
           if(((Class) type.getKey()).isInstance(target)) {
               collider = (Collider) type.getValue();
               break;
            }
        }

        return collider.canMove(target, newLocation);
    }

    @Override
    public List<Collision> getCollision(Entity target, Location newLocation) {
        Collider collider = null;
        for(Pair type : colliders) {
            if(((Class) type.getKey()).isInstance(target)) {
                collider = (Collider) type.getValue();
                break;
            }
        }

        return collider.getCollision(target, newLocation);
    }

}
