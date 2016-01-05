package com.gumbelware.treasureHunter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.clutch.dates.StringToTime;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author Matt Gumbel
 *
 */
public class ProcessData {

    /** The total horizontal distance East/West; West is negative */
    private double horizontalDistance = 0;

    /** The total vertical distance North/South; South is negative */
    private double verticalDistance = 0;

    public Instruction getResultInstruction() {
        return new Instruction(verticalDistance, horizontalDistance);
    }

    public String getResultString() {
        return "Go " + getResultInstruction().toString();
    }

    public boolean processInstructions(CSVReader reader) {

        try {
            // an array of values from the line
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {

                try {
                    // The first value is the transportation mode
                    String modeName = nextLine[0].replaceAll(" ", "_").toUpperCase();
                    Mode mode = Mode.valueOf(modeName);

                    // The second value is the duration; convert to milliseconds
                    long durationInMillis = (long) StringToTime.time(nextLine[1]) - System.currentTimeMillis();

                    // The third value is the direction
                    Direction direction = Direction.valueOf(nextLine[2].trim());

                    // Create an instruction
                    Instruction instruction = new Instruction(durationInMillis, mode, direction);

                    // For Debugging
                    System.out.println(nextLine[0] + "," + nextLine[1] + "," + nextLine[2] + " --> " + instruction);

                    // Append the vertical and horizontal distances
                    verticalDistance += instruction.getVerticalDistance();
                    horizontalDistance += instruction.getHorizontalDistance();

                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // Don't care
                }
            }
        }
        return false;
    }

    public boolean processInstructions(String path) {
        try {
            return processInstructions(new CSVReader(new FileReader(path)));
        } catch (FileNotFoundException e) {
            System.out.println("File " + path + " could not be found.");
        }
        return false;
    }
}
