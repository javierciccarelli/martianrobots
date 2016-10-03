
package com.redbadger.codingchallenge.robots;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * Created by javier on 02/10/16.
 */
public class Robot {

    private static Map<String, UnaryOperator<Position>> commands = new HashMap<>();

    static {
        commands.put("L", pos -> new Position(pos.x, pos.y, pos.direction + Math.PI / 2));
        commands.put("R", pos -> new Position(pos.x, pos.y, pos.direction - Math.PI / 2));
        commands.put("F", pos -> new Position(pos.x + (int) Math.cos(pos.direction),
                                              pos.y + (int) Math.sin(pos.direction),
                                              pos.direction));

    }

    private Position position;
    private boolean lost = false;


    public Robot(int x, int y, String direction) {
        this.position = new Position(x, y, direction);

    }

    public Position getPosition() {
        return position;
    }

    public Position execute(String commandName) {
        UnaryOperator<Position> command = this.commands.get(commandName);

        this.position = command.apply(position);
        return this.position;
    }

    public void setLost() {
        this.lost = true;
    }

    public boolean isLost() {
        return lost;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public static class Position {
        private int x, y;
        private double direction = 0;

        private static Map<String, Double> angleByDirection = new HashMap<String, Double>();
        private static Map<Double, String> directionByAngle = new HashMap<Double, String>();

        static {
            angleByDirection.put("E", 0D);
            angleByDirection.put("N", Math.PI / 2);
            angleByDirection.put("W", Math.PI);
            angleByDirection.put("S", Math.PI * 3 / 2);

            directionByAngle = angleByDirection.entrySet()
                    .stream()
                    .collect(Collectors.toMap(e -> e.getValue(), e -> e.getKey()));
        }

        public Position(int x, int y, String direction) {
           this(x, y, angleByDirection.get(direction));
        }

        public Position(int x, int y, double direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        private int numberOfFullRotations() {
            return (int) (this.direction / (2 * Math.PI));
        }

        public String getDirectionAsString() {

            reduceDirectionAngleTo0and2Pi();

            return directionByAngle.get(this.direction);
        }

        private void reduceDirectionAngleTo0and2Pi() {
            while (numberOfFullRotations() > 0)
                this.direction = this.direction - (2 * Math.PI);

            while (numberOfFullRotations() < 0 || this.direction < 0)
                this.direction = this.direction + (2 * Math.PI);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Position position = (Position) o;

            if (x != position.x) return false;
            if (y != position.y) return false;
            return position.getDirectionAsString().equals(getDirectionAsString());

        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            result = 31 * result + getDirectionAsString().hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    ", direction='" + getDirectionAsString() + '\'' +
                    '}';
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
