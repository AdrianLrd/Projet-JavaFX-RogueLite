package fr.roguelite.model.entitiy;

import fr.roguelite.model.attribute.AttributeMap;

public abstract class Entity {
    /**
     * The Location of the Entity
     */
    protected Location location = new Location();

    /**
     * The BoudingBox of the Entity
     */
    protected BoundingBox boundingBox;

    /**
     * The Attributes of the Entity
     */
    protected AttributeMap attributes = new AttributeMap();

    /**
     * The texture path for the sprite
     */
    protected String texturePath;

    /**
     * The entity sprite
     */
    protected EntitySprite sprite;

    public Location getLocation() {
        return location;
    }

    public AttributeMap getAttributes() {
        return attributes;
    }

    public EntitySprite getSprite() {
        return sprite;
    }

    public String gettexturePath() {
        return texturePath;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    /**
     * Create an Entity with the given texture path
     * @param texturePath the texture path for the sprite
     */
    public Entity(String texturePath) {
        this.texturePath = texturePath;
        sprite = new EntitySprite(this);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
