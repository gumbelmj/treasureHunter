package com.gumbelware.treasureHunter;

import java.text.DecimalFormat;

/**
 * The Representation of an Instruction. It provides a distance and direction
 *
 * @author Matt Gumbel
 *
 */
public class Instruction {
    /** Formatter for displaying numbers */
    private static final DecimalFormat df = new DecimalFormat("0.00");;

    /** Conversion from miles per hour to miles per millisecond */
    private static final double K = 0.000000277777778;

    /** The degrees of the direction of travel */
    private final double degrees;

    /** The cardinal direction of travel */
    private final Direction direction;

    /** Direct distance in miles */
    private final double distance;

    /** Horizontal (East/West) distance in miles */
    private final double horizontalDistance;

    /** Vertical (North/South) distance in miles */
    private final double verticalDistance;

    /**
     * Create an instruction
     *
     * @param verticalDistance
     *            the distance, in miles, to travel North or South. South is
     *            represented by a negative number
     * @param horizontalDistance
     *            the distance, in miles, to travel East or West. West is
     *            represented by a negative number
     */
    public Instruction(double verticalDistance, double horizontalDistance) {
        this.verticalDistance = verticalDistance;
        this.horizontalDistance = horizontalDistance;
        // c = sqrt(a^2 + b^2)
        distance = Math.sqrt(Math.pow(verticalDistance, 2) + Math.pow(horizontalDistance, 2));

        // Determine the degrees of the direction
        double radians = Math.atan(verticalDistance / horizontalDistance);
        double degrees = Math.toDegrees(radians);

        if (horizontalDistance < 0) {
            this.degrees = degrees + 180;
        } else {
            this.degrees = degrees < 0 ? 360 + degrees : degrees;
        }

        // Determine the direction that is the closet to the degrees
        Direction closestDirection = null;
        double difference = Double.MAX_VALUE;
        for (Direction direction : Direction.values()) {
            double diff = Math.abs(this.degrees - direction.getDegrees());
            if (diff < difference) {
                // This one is closer than the previous one; save it
                difference = diff;
                closestDirection = direction;
            }
        }
        // Used for Debugging only
        // System.out.println("Closest Direction to " + degrees + " is " +
        // closestDirection.name() + " at "
        // + closestDirection.getDegress() + " with a delta of " + difference);
        direction = closestDirection;
    }

    /**
     * Creates an instruction
     *
     * @param duration
     *            time in milliseconds
     * @param mode
     *            the mode of transportation
     * @param direction
     *            the cardinal direction of travel
     */
    public Instruction(long duration, Mode mode, Direction direction) {
        // Convert from miles per hour to miles per millisecond
        double speed = new Double(mode.getSpeed()).doubleValue() * K;

        // Determine the distance
        double dist = speed * new Long(duration).doubleValue();
        distance = dist;

        // set the direction
        this.degrees = direction.getDegrees();
        this.direction = direction;

        // Set the vertical and horizontal distances
        verticalDistance = dist * direction.getVerticalMultipler();
        horizontalDistance = dist * direction.getHorizontalMultipler();
    }

    public double getDegrees() {
        return degrees;
    }

    public Direction getDirection() {
        return direction;
    }

    public double getDistance() {
        return distance;
    }

    /**
     * Number that represents the Horizontal (East-West) Distance to be
     * traveled. East is represented by a positive number; West is represented
     * by a negative number.
     *
     * @return double
     */
    public double getHorizontalDistance() {
        return horizontalDistance;
    }

    /**
     * Number that represents the Vertical (North-South) Distance to be
     * traveled. North is represented by a positive number; South is represented
     * by a negative number
     *
     * @return double
     */
    public double getVerticalDistance() {
        return verticalDistance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(df.format(getDistance()));
        sb.append(" miles to the ");
        sb.append(getDirection().getTitle());
        sb.append(" at a ");
        sb.append(df.format(getDegrees()));
        sb.append(" degree angle");
        sb.append(" (");
        sb.append(df.format(Math.abs(getVerticalDistance())));
        sb.append(" miles to the ");
        sb.append(getVerticalDistance() >= 0 ? "North" : "South");
        sb.append(" and ");
        sb.append(df.format(Math.abs(getHorizontalDistance())));
        sb.append(" miles to the ");
        sb.append(getHorizontalDistance() >= 0 ? "East" : "West");
        sb.append(")");
        return sb.toString();
    }
}
