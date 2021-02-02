package fr.roguelite.model.entitiy;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents an 2D Location in the Window
 * @author Timothé BERTRAND - Adrian LARDON
 */
public class Location {
    /**
     * The property containing the x coordinate
     */
    private SimpleIntegerProperty x;

    /**
     * The property containing the y coordinate
     */
    private SimpleIntegerProperty y;

    /**
     * @hidden
     */
    private int _x;

    /**
     * @hidden
     */
    private int _y;

    /**
     * Creates a location with the given coordinates
     * @param x The x coordinate
     * @param y the y coordinate
     */
    public Location(int x, int y) {
        setX(x);
        setY(y);
    }

    /**
     * Create an empty location
     */
    public Location() {

    }

    /**
     * Get the x coordinate
     * @return An Integer representing the x coordinate in the window
     */
    public int getX()
    {
        if (x == null)
            return _x;
        else
            return x.get();
    }

    /**
     * Sets the x coordinate
     * @param value An Integer representing the x coordinate in the window
     */
    public void setX(int value)
    {
        if (x == null)
            _x = value;
        else
            x.set(value);
    }

    /**
     * Gets the x property
     * @return A JavaFX property
     */
    public IntegerProperty xProperty()
    {
        if (x == null)
            x = new SimpleIntegerProperty(this, "x", _x);
        return x;
    }

    /**
     * Get the y coordinate
     * @return An Integer representing the y coordinate in the window
     */
    public int getY()
    {
        if (y == null)
            return _y;
        else
            return y.get();
    }

    /**
     * Sets the y coordinate
     * @param value An Integer representing the y coordinate in the window
     */
    public void setY(int value)
    {
        if (y == null)
            _y = value;
        else
            y.set(value);
    }

    /**
     * Gets the y property
     * @return A JavaFX property
     */
    public IntegerProperty yProperty()
    {
        if (y == null)
            y = new SimpleIntegerProperty(this, "y", _y);
        return y;
    }

    /**
     * Duplicate a location
     * @return A copy of the Location
     */
    public Location clone() {
        return new Location(getX(), getY());
    }

    /**
     * Convert the location to a readable object
     * @return A String representing the information of the Location
     */
    @Override
    public String toString() {
        return "Location (" +
                "x:" + getX() +
                "  y:" + getY() +
                ')';
    }
}
