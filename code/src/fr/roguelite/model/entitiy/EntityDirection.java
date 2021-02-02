package fr.roguelite.model.entitiy;

public enum EntityDirection {
    LEFT(-1),
    RIGHT(1);

    /**
     * The Value given to the ScaleX Property to flip the Entity
     */
    private int value;


    EntityDirection(int i) {
        value = i;
    }


    public int getValue() {
        return value;
    }

    /**
     * Get the opposite value from the Direction
     * @return An EntityDirection
     */
    public EntityDirection getOpposite() {
        return values()[(ordinal() + 1) % (values().length )];
    }
}
