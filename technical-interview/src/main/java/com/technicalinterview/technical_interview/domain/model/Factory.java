package com.technicalinterview.technical_interview.domain.model;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;
import java.util.Collection;
import java.util.Comparator;

public class Factory {
    private Map<Position, Robot> cells;
    private int width;
    private int height;

    public Factory(int width, int height) {
        this.cells = new HashMap<>();
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean placeRobot(Position position, Robot robot) {
        if (position.getX() >= width || position.getY() >= height || position.getX() < 0 || position.getY() < 0) {
            return false;
        }
        if (cells.containsKey(position)) {
            return false;
        }
        cells.put(position, robot);
        return true;
    }

    public Collection<Robot> getRobots() {
        return cells.values();
    }

    public boolean isOccupied(Position pos) {
        return cells.containsKey(pos);
    }

    public boolean isPositionValid(Position pos) {
        return pos.getX() >= 0 && pos.getX() <= width && pos.getY() >= 0 && pos.getY() <= height;
    }

    public boolean moveRobot(Robot robot) {
        Position currentPos = robot.getPosition();
        Position nextPos = currentPos.moveForward(robot.getOrientation());

        if (!isPositionValid(nextPos)) {
            // if next position is out of bounds, we omit the command.
            return true;
        } else if (isOccupied(nextPos)) {
            // if next position is blocked by another robot, cancel the robot movement.
            return false;
        }
        cells.remove(currentPos);
        cells.put(nextPos, robot);
        robot.setPosition(nextPos);
        return true;
    }

    public String getRobotsPositionString() {
        // Return the robot positions in the required format
        StringBuilder result = new StringBuilder();
        List<Robot> sortedRobotList = cells.values().stream()
                .sorted(Comparator.comparingInt(Robot::getOrderId))
                .collect(Collectors.toList());
        for (Robot r : sortedRobotList) {
            result.append(r.getPositionString());
        }
        return result.toString();
    }
}
