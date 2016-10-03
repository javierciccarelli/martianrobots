package com.redbadger.codingchallenge.robots;

import java.util.*;

/**
 * Created by javier on 02/10/16.
 */
public class RobotsManager {
    private World world;
    private Stack<Robot> robots = new Stack<>();
    private Set<Scent> scents = new HashSet<>();

    public RobotsManager(int maxWidth, int maxHeight) {
        if (maxWidth > 50 || maxHeight > 50)
            throw new IllegalArgumentException("Maximum world size is 50x50");

        this.world = new World(maxWidth, maxHeight);
    }

    public void addRobot(int initX, int initY, String direction) {
        this.robots.push(new Robot(initX, initY, direction));
    }

    public List<Robot> getRobots() {
        return new ArrayList<Robot>(robots);
    }

    public Robot getActiveRobot() {

        if (robots.empty())
            return null;

        return robots.peek();
    }

    public boolean execute(String command) {

        if (getActiveRobot().isLost())
            return false;

        Robot.Position oldPosition = this.getActiveRobot().getPosition();

        if (isAScent(command, oldPosition))
            return true;

        Robot.Position newPosition = this.getActiveRobot().execute(command);

        if (!world.contains(newPosition)) {
            this.getActiveRobot().setLost();
            this.scents.add(new Scent(command, oldPosition));
            this.getActiveRobot().setPosition(oldPosition);
        }
        return true;
    }

    private boolean isAScent(String command, Robot.Position oldPosition) {
        return this.scents.contains(new Scent(command, oldPosition));
    }

    private static class Scent {
        private String command;
        private Robot.Position position;

        public Scent(String command, Robot.Position position) {
            this.command = command;
            this.position = position;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Scent scent = (Scent) o;

            if (!command.equals(scent.command)) return false;
            return position.equals(scent.position);

        }

        @Override
        public int hashCode() {
            int result = command.hashCode();
            result = 31 * result + position.hashCode();
            return result;
        }
    }

    private static class World {
        private int maxX;
        private int maxY;

        public World(int maxWidth, int maxHeight) {
            this.maxX = maxWidth;
            this.maxY = maxHeight;
        }

        private boolean contains(Robot.Position pos) {
            return pos.getX() <= maxX && pos.getY() <= maxY;
        }

    }
}
