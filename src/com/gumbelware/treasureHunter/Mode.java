package com.gumbelware.treasureHunter;

/**
 * Represents a mode of transportation
 *
 * @author Matt Gumbel
 *
 */
public enum Mode {
    ELEPHANT_RIDE("Elephant Ride", 6), HORSE_GALLOP("Horse Gallop", 15), HORSE_TROT("Horse Trot", 4), RUN("Run",
            6), WALK("Walk", 3);

    /** Speed of the mode of transportation in miles per hour */
    private int speed;

    /** Description of the mode of transportation */
    private String type;

    private Mode(String type, int speed) {
        this.speed = speed;
        this.type = type;
    }

    public int getSpeed() {
        return speed;
    }
};
