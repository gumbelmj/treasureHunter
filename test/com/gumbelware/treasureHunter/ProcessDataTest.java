package com.gumbelware.treasureHunter;

import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

import au.com.bytecode.opencsv.CSVReader;
import junit.framework.TestCase;

/**
 * @author Matt Gumbel
 *
 */
public class ProcessDataTest extends TestCase {

    @Test
    public void testDirections() {
        // Test that all the instructions direction calculations are correct
        for (Direction direction : Direction.values()) {
            ProcessData instance = new ProcessData();
            CSVReader reader = new CSVReader(new StringReader("Walk, 20 mins, " + direction.name()));
            instance.processInstructions(reader);
            Instruction instruction = instance.getResultInstruction();
            Assert.assertEquals("Degrees for " + direction.name() + " are wrong", direction.getDegrees(),
                    instruction.getDegrees(), 0.0001);
            Assert.assertEquals("Direction for " + direction.name() + " is wrong", direction,
                    instruction.getDirection());
            Assert.assertEquals("Distance for " + direction.name() + " is wrong", 1, instruction.getDistance(), 0.0001);
        }
    }

    @Test
    public void testModes() {
        // Try each mode for one mile
        ProcessData instance = new ProcessData();
        String instructions = "";
        instructions += "Elephant Ride, 10 mins, N\n";
        instructions += "Horse Gallop, 4 mins, N\n";
        instructions += "Horse Trot, 15 mins, N\n";
        instructions += "Run, 10 mins, N\n";
        instructions += "Walk, 20 mins, N\n";
        CSVReader reader = new CSVReader(new StringReader(instructions));
        instance.processInstructions(reader);
        Instruction instruction = instance.getResultInstruction();
        Assert.assertEquals("Distance is wrong", 5, instruction.getDistance(), 0.0001);
        Assert.assertEquals("Direction is wrong", Direction.N, instruction.getDirection());
        Assert.assertEquals("Degrees are wrong", 90, instruction.getDegrees(), 0.0001);
    }

    @Test
    public void testReturnHome() {
        // Travel each direction for one mile and return home
        ProcessData instance = new ProcessData();
        String instructions = "";
        for (Direction direction : Direction.values()) {
            instructions += "Walk, 20 mins, " + direction.name() + "\n";
        }
        CSVReader reader = new CSVReader(new StringReader(instructions));
        instance.processInstructions(reader);
        Instruction instruction = instance.getResultInstruction();
        Assert.assertEquals("Distance is wrong", 0, instruction.getDistance(), 0.0001);
    }

    @Test
    public void testTimes() {
        // Test various times
        ProcessData instance = new ProcessData();
        String instructions = "";
        instructions += "Run, 2 hours, N\n";
        instructions += "Run, 1 hour 30 minutes, N\n";
        instructions += "Run, 3 hr, N\n";
        instructions += "Run, 90 min, N\n";
        instructions += "Run, 1 day, N\n";
        instructions += "Run, 30 seconds, N\n";
        CSVReader reader = new CSVReader(new StringReader(instructions));
        instance.processInstructions(reader);
        Instruction instruction = instance.getResultInstruction();
        Assert.assertEquals("Distance is wrong", 192.05, instruction.getDistance(), 0.0001);
        Assert.assertEquals("Direction is wrong", Direction.N, instruction.getDirection());
        Assert.assertEquals("Degrees are wrong", 90, instruction.getDegrees(), 0.0001);
    }
}
