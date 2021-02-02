package fr.roguelite.model.entitiy;

import fr.roguelite.model.entitiy.entities.*;

public enum EntityType {
    PLAYER(Player.class),

    // FLOOR 1
    MINI_ZOMBIE(MiniZombie.class),
    ZOMBIE(Zombie.class),
    SKELETON(Skeleton.class),
    SLIME(Slime.class),
    BIG_ZOMBIE(BigZombie.class),

    // FLOOR 2
    GOBLIN(Goblin.class),
    WARRIOR_ORC(Worc.class),
    MASKED_ORC(Morc.class),
    SHAMAN_ORC(Shorc.class),
    OGRE(Ogre.class),

    // FLOOR 3
    IMP(Imp.class),
    CHORT(Chort.class),
    WOGOL(Wogol.class),
    NECROMANCER(Necromancer.class),
    DEMON(Demon.class);

    private Class entityClass;

    <T extends Entity> EntityType(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Gets the Entity class of the Type
     * @return The Entity associed
     */
    public <T extends Entity> Class<T> getEntityClass() {
        return entityClass;
    }
}
