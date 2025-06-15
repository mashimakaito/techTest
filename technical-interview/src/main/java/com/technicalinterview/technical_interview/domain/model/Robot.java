package com.technicalinterview.technical_interview.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    private int orderId;
    private Position position;

    private Orientation orientation;
    private List<Command> actionChart;

    private int currentActionIndex;

    public Robot() {
    }

    public Robot(Position position, Orientation orientation, int orderId) {
        this.position = position;
        this.orientation = orientation;
        this.actionChart = new ArrayList<>();
        this.currentActionIndex = 0;
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public List<Command> getActionChart() {
        return actionChart;
    }

    public void setActionChart(List<Command> actionChart) {
        this.actionChart = actionChart;
    }

    public int getCurrentActionIndex() {
        return currentActionIndex;
    }

    public void setCurrentActionIndex(int currentActionIndex) {
        this.currentActionIndex = currentActionIndex;
    }

    public void turnLeft() {
        orientation = orientation.turnLeft();
    }

    public void turnRight() {
        orientation = orientation.turnRight();
    }

    public String getPositionString() {
        return String.format("%d %d %s\n", position.getX(), position.getY(), orientation.name().charAt(0));
    }

}
