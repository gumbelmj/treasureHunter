package com.gumbelware.treasureHunter;

/**
 * Provides the common cardinal directions
 *
 * @author Matt Gumbel
 *
 */
public enum Direction {

    E("East", 0), ENE("East-Northeast", 22.5), ESE("East-Southeast", 337.5), N("North", 90), NE("Northeast",
            45), NNE("North-Northeast", 67.5), NNW("North-Northwest", 112.5), NW("Northwest", 135), S("South", 270), SE(
                    "Southeast", 315), SSE("South-Southeast", 292.5), SSW("South-Southwest", 247.5), SW("Southwest",
                            225), W("West", 180), WNW("West-Northwest", 157.5), WSW("West-Southwest", 202.5);

    /** The direction represented as a degree */
    private double degrees;

    /** The multiplier of a distance to reveal just the horizontal component */
    private double horizontalMultipler;

    /** The display name of the direction */
    private String title;

    /** The multiplier of a distance to reveal just the vertical component */
    private double verticalMultipler;

    private Direction(String title, double degrees) {
        this.title = title;
        this.degrees = degrees;
        this.verticalMultipler = Math.sin(Math.toRadians(degrees));
        this.horizontalMultipler = Math.sin(Math.toRadians(90 - degrees));
    }

    public double getDegrees() {
        return degrees;
    }

    public double getHorizontalMultipler() {
        return horizontalMultipler;
    }

    public String getTitle() {
        return title;
    }

    public double getVerticalMultipler() {
        return verticalMultipler;
    }
}
