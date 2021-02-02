package fr.roguelite.model.entitiy;

public enum Animation {
    NONE(1),
    IDLE(4),
    WALKING(4);

    /**
     * Number of frames in the Animation
     */
    private int nbFrames;

    /**
     * @hidden
     */
    Animation(int nbFrames) {
        this.nbFrames = nbFrames;
    }

    /**
     * Get the Number of frames
     * @return An Integer corresponding to the number of frames in the current animation
     */
    public int getNbFrames() {
        return nbFrames;
    }
}
