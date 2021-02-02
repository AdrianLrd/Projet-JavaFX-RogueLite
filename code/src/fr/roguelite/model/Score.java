package fr.roguelite.model;

import java.io.Serializable;

public class Score implements Serializable {
    /**
     * The pseudo of the player
     */
    private String pseudo;

    /**
     * The time that the player survived
     */
    private String time;

    /**
     * The date when the player played
     */
    private String date;

    /**
     * Create a new Score for the given Parameter
     * @param pseudo The pseudo of the player
     * @param time The time that the player survived
     * @param date The date when the player played
     */
    public Score(String pseudo, String time, String date) {
        this.pseudo = pseudo;
        this.time = time;
        this.date = date;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
}
