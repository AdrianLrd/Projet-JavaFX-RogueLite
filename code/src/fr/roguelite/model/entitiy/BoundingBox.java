package fr.roguelite.model.entitiy;

public class BoundingBox {
    /**
     * The top left location of the BoundingBox
     */
    private Location origin;

    /**
     * The x offset from the top left corner where the BoundingBox should start
     */
    private int offsetx;

    /**
     * The y offset from the top left corner where the BoundingBox should start
     */
    private int offsety;

    /**
     * The width of the BoundingBox
     */
    private int dx;

    /**
     * The height of the BoundingBox
     */
    private int dy;

    /**
     * Creates a new BoundingBox
     * @param topLeft The top left corner
     * @param bottomRight The bottom right corner
     */
    public BoundingBox(Location topLeft, Location bottomRight) {
        this.origin = topLeft;
        this.dx = bottomRight.getX() - topLeft.getX();
        this.dy = bottomRight.getY() - topLeft.getY();
    }

    /**
     * Create a new BoundingBox
     * @param topLeft The top left corner
     * @param dx The width of the BoundingBox
     * @param dy The height of the BoundingBox
     */
    public BoundingBox(Location topLeft, int dx, int dy) {
        this.origin = topLeft;
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Create a new BoundingBox
     * @param origin The top left corner
     * @param offsetx The x offset from the top left corner where the BoundingBox should start
     * @param offsety The y offset from the top left corner where the BoundingBox should start
     * @param dx The width of the BoundingBox
     * @param dy The height of the BoundingBox
     */
    public BoundingBox(Location origin, int offsetx, int offsety, int dx, int dy) {
        this.origin = origin;

        this.offsetx = offsetx;
        this.offsety = offsety;

        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets the top left corner of the BoundingBox, add offsets
     * @return The location of the top left corner
     */
    public Location getTopLeft() {
        return new Location(origin.getX() + offsetx,      origin.getY() + offsety);
    }

    /**
     * Gets the top right corner of the BoundingBox, add offsets
     * @return The location of the top right corner
     */
    public Location getTopRight() {
        return new Location(origin.getX() + offsetx + dx, origin.getY() + offsety);
    }

    /**
     * Gets the bottom right corner of the BoundingBox, add offsets
     * @return The location of the bottom right corner
     */
    public Location getBottomRight() {
        return new Location(origin.getX() + offsetx + dx, origin.getY() + offsety + dy);
    }

    /**
     * Gets the bottom left corner of the BoundingBox, add offsets
     * @return The location of the bottom left corner
     */
    public Location getBottomLeft() {
        return new Location(origin.getX() + offsetx,      origin.getY() + offsety + dy);
    }

    /**
     * Check if an BoundingBox is containing another one
     * @param other The other BoundingBox
     * @return True if the BoundingBox contains another one or false
     */
    public boolean contains(BoundingBox other) {
        if(getTopLeft().getX() > other.getTopRight().getX()
        || getTopRight().getX() < other.getTopLeft().getX()) return false;

        if(getTopLeft().getY() >  other.getBottomLeft().getY()
        || getBottomLeft().getY() < other.getTopLeft().getY()) return false;

        return true;
    }

    public boolean collide(BoundingBox other) {
        if(getTopLeft().getX() < other.getTopLeft().getX() && getTopRight().getX() > other.getTopLeft().getX()) return true;
        if(getTopLeft().getX() < other.getTopRight().getX() && getTopRight().getX() > other.getTopRight().getX()) return true;

        if(getTopLeft().getY() < other.getTopLeft().getY() && getBottomLeft().getY() > other.getTopLeft().getY()) return true;
        if(getTopLeft().getY() < other.getBottomLeft().getY() && getBottomLeft().getY() > other.getBottomLeft().getY()) return true;

        return false;
    }

    public int getOffsetx() {
        return offsetx;
    }

    public int getOffsety() {
        return offsety;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public void setOffsetx(int offsetx) {
        this.offsetx = offsetx;
    }

    public void setOffsety(int offsety) {
        this.offsety = offsety;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return
                "BoundingBox {\n" +
                    getTopLeft().getX() + ":" +getTopLeft().getY() + "\n" +
                    getBottomRight().getX() + ":" + getBottomRight().getY() + "\n" +
                '}';
    }

    public BoundingBox clone() {
        return new BoundingBox(origin, offsetx, offsetx, dx, dy);
    }
}
