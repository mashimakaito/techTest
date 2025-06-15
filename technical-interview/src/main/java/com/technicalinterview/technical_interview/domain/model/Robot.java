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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + orderId;
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
        result = prime * result + ((actionChart == null) ? 0 : actionChart.hashCode());
        result = prime * result + currentActionIndex;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Robot other = (Robot) obj;
        if (orderId != other.orderId)
            return false;
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        if (orientation != other.orientation)
            return false;
        if (actionChart == null) {
            if (other.actionChart != null)
                return false;
        } else if (!actionChart.equals(other.actionChart))
            return false;
        if (currentActionIndex != other.currentActionIndex)
            return false;
        return true;
    }

    public String getPositionString() {
        return String.format("%d %d %s\n", position.getX(), position.getY(), orientation.name().charAt(0));
    }

}
