package com.redbadger.codingchallenge.robots;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by javier on 02/10/16.
 */
public class RobotTest {

    @Test
    public void robotShouldBeCreatedProperly() {

        Robot robot = new Robot(1, 1, "N");

        assertNotNull(robot);
        assertEquals(new Robot.Position(1, 1, "N"), robot.getPosition());
    }

    @Test
    public void shouldBeAbleToExecuteLeftCommand() {
        Robot robot = new Robot(1, 1, "W");
        assertEquals(new Robot.Position(1, 1, "S"), robot.execute("L"));
    }

    @Test
    public void shouldBeAbleToExecuteRightCommand() {
        Robot robot = new Robot(1, 1, "N");
        assertEquals(new Robot.Position(1, 1, "E"), robot.execute("R"));
    }

    @Test
    public void shouldBeAbleToExecuteForwardCommand() {
        Robot robot = new Robot(1, 1, "N");
        assertEquals(new Robot.Position(1, 2, "N"), robot.execute("F"));
    }

    @Test
    public void shouldBeAbleToSetAsLost() {
        Robot robot = new Robot(1, 1, "N");
        robot.setLost();
        assertTrue(robot.isLost());
    }
}
