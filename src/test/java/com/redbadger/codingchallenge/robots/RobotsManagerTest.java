package com.redbadger.codingchallenge.robots;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by javier on 02/10/16.
 */
public class RobotsManagerTest {

    @Test
    public void shouldAllowMAnagerCreation() {
        RobotsManager manager = new RobotsManager(5,6);
        assertNotNull(manager);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowWorldWidthGreaterThan50() {
        RobotsManager manager = new RobotsManager(51,6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowWorldLengthGreaterThan50() {
        RobotsManager manager = new RobotsManager(31,51);
    }

    @Test
    public void shouldAllowWorld50by50() {
        RobotsManager manager = new RobotsManager(50,50);
        assertNotNull(manager);

    }

    @Test
    public void shouldAllowAddingOfRobot() {
        RobotsManager manager = new RobotsManager(10,10);
        manager.addRobot(0, 0, "N");

        assertNotNull(manager.getRobots());
        assertEquals(1, manager.getRobots().size());

    }

    @Test
    public void shouldMoveRobotOnLeftInstruction() {
        RobotsManager manager = new RobotsManager(10,10);
        manager.addRobot(0, 0, "N");
        manager.execute("L");
        assertEquals(new Robot.Position(0, 0,"W"), manager.getActiveRobot().getPosition());
        manager.execute("L");
        assertEquals(new Robot.Position(0, 0,"S"), manager.getActiveRobot().getPosition());
        manager.execute("L");
        assertEquals(new Robot.Position(0, 0,"E"), manager.getActiveRobot().getPosition());
        manager.execute("L");
        assertEquals(new Robot.Position(0, 0,"N"), manager.getActiveRobot().getPosition());

    }

    @Test
    public void shouldMoveRobotOnRightInstruction() {
        RobotsManager manager = new RobotsManager(10,10);
        manager.addRobot(0, 0, "N");
        manager.execute("R");
        assertEquals(new Robot.Position(0, 0,"E"), manager.getActiveRobot().getPosition());
        manager.execute("R");
        assertEquals(new Robot.Position(0, 0,"S"), manager.getActiveRobot().getPosition());
        manager.execute("R");
        assertEquals(new Robot.Position(0, 0,"W"), manager.getActiveRobot().getPosition());
        manager.execute("R");
        assertEquals(new Robot.Position(0, 0,"N"), manager.getActiveRobot().getPosition());
        manager.execute("R");
        assertEquals(new Robot.Position(0, 0,"E"), manager.getActiveRobot().getPosition());
        manager.execute("R");
        assertEquals(new Robot.Position(0, 0,"S"), manager.getActiveRobot().getPosition());
    }

    @Test
    public void shouldMoveRobotVerticalOnForwardInstruction() {
        RobotsManager manager = new RobotsManager(10,10);
        manager.addRobot(0, 0, "N");
        manager.execute("F");
        assertEquals(new Robot.Position(0, 1,"N"), manager.getActiveRobot().getPosition());
        manager.execute("F");
        assertEquals(new Robot.Position(0, 2,"N"), manager.getActiveRobot().getPosition());
        manager.execute("F");
        assertEquals(new Robot.Position(0, 3,"N"), manager.getActiveRobot().getPosition());
        manager.execute("F");
        assertEquals(new Robot.Position(0, 4,"N"), manager.getActiveRobot().getPosition());
        manager.execute("F");
        assertEquals(new Robot.Position(0, 5,"N"), manager.getActiveRobot().getPosition());

    }

    @Test
    public void shouldMoveRobotHorizontalyOnForwardInstruction() {
        RobotsManager manager = new RobotsManager(10,10);
        manager.addRobot(0, 0, "E");
        manager.execute("F");
        assertEquals(new Robot.Position(1, 0,"E"), manager.getActiveRobot().getPosition());
        manager.execute("F");
        assertEquals(new Robot.Position(2, 0,"E"), manager.getActiveRobot().getPosition());
        manager.execute("F");
        assertEquals(new Robot.Position(3, 0,"E"), manager.getActiveRobot().getPosition());
        manager.execute("F");
        assertEquals(new Robot.Position(4, 0,"E"), manager.getActiveRobot().getPosition());
        manager.execute("F");
        assertEquals(new Robot.Position(5, 0,"E"), manager.getActiveRobot().getPosition());

    }


    @Test
    public void shouldExecuteExampleProperly() {
        RobotsManager manager = new RobotsManager(5, 3);
        manager.addRobot(1, 1, "E");
        manager.execute("R");
        manager.execute("F");
        manager.execute("R");
        manager.execute("F");
        manager.execute("R");
        manager.execute("F");
        manager.execute("R");
        manager.execute("F");

        assertEquals(new Robot.Position(1, 1,"E"), manager.getActiveRobot().getPosition());

        manager.addRobot(3, 2, "N");
        manager.execute("F");
        manager.execute("R");
        manager.execute("R");
        manager.execute("F");
        manager.execute("L");
        manager.execute("L");

        manager.execute("F");
        manager.execute("F");
        manager.execute("R");
        manager.execute("R");
        manager.execute("F");
        manager.execute("L");
        manager.execute("L");

        assertEquals(new Robot.Position(3, 3,"N"), manager.getActiveRobot().getPosition());
        assertTrue(manager.getActiveRobot().isLost());

        manager.addRobot(0, 3, "W");
        manager.execute("L");
        manager.execute("L");
        manager.execute("F");
        manager.execute("F");
        manager.execute("F");
        manager.execute("L");
        manager.execute("F");
        manager.execute("L");
        manager.execute("F");
        manager.execute("L");

        assertEquals(new Robot.Position(2, 3,"S"), manager.getActiveRobot().getPosition());

    }


}
