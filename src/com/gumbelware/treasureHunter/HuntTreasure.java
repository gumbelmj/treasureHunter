package com.gumbelware.treasureHunter;

/**
 * Main Class for Treasure Hunting Simplifier
 *
 * @author Matt Gumbel
 *
 */
public class HuntTreasure {

    public static void main(String... arg) {

        if (arg.length == 1) {
            ProcessData processData = new ProcessData();
            if (processData.processInstructions(arg[0])) {
                System.out.println("===================================================");
                System.out.println(processData.getResultString());
                System.out.println("===================================================");
            } else {
                System.out.println("There was a problem processing the file.");
            }
        } else {
            System.out.println("To use this application a CSV file must be supplied.");
            System.out.println();
            System.out.println("The columns of the CSV are:");
            System.out.println("Mode, Duration, Direction");
            System.out.println();
            System.out.println("Possible Modes are: Walk, Run, Horse trot, Horse gallop or Elephant ride");
            String values = "";
            for (Direction direction : Direction.values()) {
                values += direction.name() + ", ";
            }
            values = values.substring(0, values.length() - 2);
            System.out.println("Possible Directions are: " + values);
            System.out.println();
            System.out.println("An example CSV is:");
            System.out.println("Walk, 20 mins, N");
            System.out.println("Run, 1 hour, E");
            System.out.println("Horse trot, 3 hours, NW");
            System.out.println("Elephant ride, 1 hour 30 mins, SE");
            System.out.println("Horse gallop, 20 mins, SE");
        }
    }
}
