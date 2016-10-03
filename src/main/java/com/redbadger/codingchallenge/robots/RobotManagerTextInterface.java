package com.redbadger.codingchallenge.robots;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by javier on 03/10/16.
 */
public class RobotManagerTextInterface {

    public String execute(String commands) {

        RobotsManager manager = parseCommandsAndExecuteCommands(commands);

        return printFinalRobotPositions(manager);
    }

    private String printFinalRobotPositions(RobotsManager manager) {
        return manager.getRobots().stream()
                .map(r -> r.getPosition().getX() + " "
                        + r.getPosition().getY() + " "
                        + r.getPosition().getDirectionAsString()
                        + (r.isLost() ? " LOST": ""))
                .collect(Collectors.joining("\n"));
    }

    private RobotsManager parseCommandsAndExecuteCommands(String commands) {

        String[] lines = commands.split("\n", 2);
        String[] worldSize = lines[0].split(" ");

        RobotsManager manager = new RobotsManager(Integer.parseInt(worldSize[0]), Integer.parseInt(worldSize[1]));

        Arrays.stream(lines[1].split("\n\n")).forEach(l -> {
            String[] robotLines = l.split("\n");
            String[] initialPosition = robotLines[0].split(" ");

            manager.addRobot(Integer.parseInt(initialPosition[0]), Integer.parseInt(initialPosition[1]), initialPosition[2]);

            // read and execute all commands
            robotLines[1].chars().mapToObj(c -> Character.toString((char) c)).forEach(c -> manager.execute(c));

        });
        return manager;
    }
}
