package com.gumbelware.treasureHunter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author Matt Gumbel
 *
 */
public class HuntTreasureTest extends TestCase {

    @Test
    public void testFileNotFound() throws IOException {

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        HuntTreasure.main("fakefile.csv");
        Assert.assertEquals("Output is incorrect",
                "File fakefile.csv could not be found.\nThere was a problem processing the file.\n",
                outContent.toString());

        // Put things back
        System.setOut(null);

    }

    @Test
    public void testFullOperation() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        HuntTreasure.main("TestRoute.csv");

        // Trim down the input to just the output we care about
        String result = outContent.toString();
        int index = result.indexOf("=");
        result = result.substring(index);
        String actualResult = result.replaceAll("=", "").trim();
        String expectedResult = "Go 2.72 miles to the East-Southeast at a 327.18 degree angle (1.47 miles to the South and 2.29 miles to the East)";
        Assert.assertEquals("Output is incorrect", expectedResult, actualResult);

        // Put things back
        System.setOut(null);
    }

    @Test
    public void testInstructions() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        HuntTreasure.main();

        // Trim down the input to just the output we care about
        String actualResult = outContent.toString();
        String expectedResult = "To use this application a CSV file must be supplied.\n";
        expectedResult += "\n";
        expectedResult += "The columns of the CSV are:\n";
        expectedResult += "Mode, Duration, Direction\n";
        expectedResult += "\n";
        expectedResult += "Possible Modes are: Walk, Run, Horse trot, Horse gallop or Elephant ride\n";
        expectedResult += "Possible Directions are: E, ENE, ESE, N, NE, NNE, NNW, NW, S, SE, SSE, SSW, SW, W, WNW, WSW\n";
        expectedResult += "\n";
        expectedResult += "An example CSV is:\n";
        expectedResult += "Walk, 20 mins, N\n";
        expectedResult += "Run, 1 hour, E\n";
        expectedResult += "Horse trot, 3 hours, NW\n";
        expectedResult += "Elephant ride, 1 hour 30 mins, SE\n";
        expectedResult += "Horse gallop, 20 mins, SE\n";
        Assert.assertEquals("Output is incorrect", expectedResult, actualResult);

        // Put things back
        System.setOut(null);

    }
}
