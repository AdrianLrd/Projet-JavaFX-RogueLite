package fr.roguelite.model;


import fr.roguelite.model.collider.Collider;
import fr.roguelite.model.collider.SimpleCollider;
import fr.roguelite.model.entitiy.generator.EntityGenerator;
import fr.roguelite.model.entitiy.generator.SimpleEntityGenerator;
import fr.roguelite.model.mover.Mover;
import fr.roguelite.model.mover.SimpleMover;

public class Manager {
    /**
     * The Entity Generator that is used to generat Entities
     */
    private EntityGenerator entityGenerator = new SimpleEntityGenerator();

    /**
     * The Mover that is used to move entities
     */
    private Mover mover = new SimpleMover();

    /**
     * The Collider that is used to move entities
     */
    private Collider collider = new SimpleCollider();

    /**
     * The current running game
     */
    private Game game = new Game(this);

    public EntityGenerator getEntityGenerator() {
        return entityGenerator;
    }

    public Mover getMover() {
        return mover;
    }

    public Collider getCollider() {
        return collider;
    }

    public Game getGame() {
        return game;
    }
}
