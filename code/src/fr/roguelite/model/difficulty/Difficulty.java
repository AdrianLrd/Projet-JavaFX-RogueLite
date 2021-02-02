package fr.roguelite.model.difficulty;


import fr.roguelite.model.entitiy.EntityType;

import static fr.roguelite.model.entitiy.EntityType.*;

public enum Difficulty {
    EASY(new EntityType[] { MINI_ZOMBIE, ZOMBIE, SKELETON, SLIME }, BIG_ZOMBIE),
    NORMAL(new EntityType[] {GOBLIN, MASKED_ORC, SHAMAN_ORC, WARRIOR_ORC}, OGRE),
    HARD(new EntityType[] {IMP, WOGOL, CHORT, NECROMANCER}, DEMON);

    /**
     * The Entity List for The given Difficulty
     */
    private EntityType monsters[];

    /**
     * The Boss for the given Difficulty
     */
    private EntityType boss;


    /**
     * @hidden
     */
    Difficulty(EntityType[] monsters, EntityType boss) {
        this.monsters = monsters;
        this.boss = boss;
    }

    /**
     * Gets the Monsters according to the difficulty
     * @return A List of EntityType
     */
    public EntityType[] getMonsters() {
        return monsters;
    }

    /**
     * Get the Boss according to the difficulty
     * @return The EntityType
     */
    public EntityType getBoss() {
        return boss;
    }

    /**
     * Get a random Monster according to the difficulty
     * @return An EntityType
     */
    public EntityType getRandomMonster() {
        return monsters[(int) (Math.random() * monsters.length)];
    }
}
