package com.redbadger.codingchallenge.robots;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by javier on 03/10/16.
 */
public class RobotManagerTextInterfaceTest {

    @Test
    public void shouldWorkForExample() {
        String input = "5 3\n" +
                        "1 1 E\n" +
                        "RFRFRFRF\n\n" +
                        "3 2 N\n" +
                        "FRRFLLFFRRFLL\n\n" +
                        "0 3 W\n" +
                        "LLFFFLFLFL\n\n";

        RobotManagerTextInterface inter = new RobotManagerTextInterface();

        String expectedOutput = "1 1 E\n3 3 N LOST\n2 3 S";

        assertEquals(expectedOutput, inter.execute(input));

    }

}
