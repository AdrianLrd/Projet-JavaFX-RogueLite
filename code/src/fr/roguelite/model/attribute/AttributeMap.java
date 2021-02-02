package fr.roguelite.model.attribute;

import java.util.HashMap;

/**
 * Represents a map containing an Attribute and it's value
 * @author Timothé BERTRAND - Adrian LARDON
 */
public class AttributeMap {

    /**
     * Represents the HashMap containing the values
     */
    private HashMap<Attribute, Object> attributes;

    /**
     * Creates an empty AttributeMap
     */
    public AttributeMap() {
       attributes = new HashMap<>();
    }

    /**
     * Sets the value for the given Attribute
     * @param attribute
     * @param value An Integer representing the x coordinate in the window
     * @see Attribute
     */
    public void set(Attribute attribute, Object value) {
        attributes.put(attribute, value);
    }

    /**
     * Get the value from the given Attribute
     * @param attribute The type of Attribute
     * @return An object corresponding to the Attribute value
     */
    private  <T> T get(Attribute attribute) {
        return (T) attributes.get(attribute);
    }

    /**
     * Get the value from the given Attribute as a String
     * @param attribute The type of Attribute
     * @return A String corresponding to the Attribute value
     */
    public String getString(Attribute attribute) {
        return get(attribute);
    }

    /**
     * Get the value from the given Attribute as an Integer
     * @param attribute The type of Attribute
     * @return An Integer corresponding to the Attribute value
     */
    public int getInt(Attribute attribute) {
        return get(attribute);
    }

    /**
     * Get the value from the given Attribute as a Flaot
     * @param attribute The type of Attribute
     * @return A Float corresponding to the Attribute value
     */
    public float getFloat(Attribute attribute) {
        return get(attribute);
    }

    /**
     * Get the value from the given Attribute as a Boolean
     * @param attribute The type of Attribute
     * @return A Boolean corresponding to the Attribute value
     */
    public boolean getBool(Attribute attribute) {
        return get(attribute);
    }
}
