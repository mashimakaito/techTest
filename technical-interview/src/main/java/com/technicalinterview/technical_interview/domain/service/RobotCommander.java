package com.technicalinterview.technical_interview.domain.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.technicalinterview.technical_interview.domain.model.Command;
import com.technicalinterview.technical_interview.domain.model.Factory;
import com.technicalinterview.technical_interview.domain.model.Robot;

/*
 * RobotCommander implementation:
 *  Domain service that executes the instructions saved to each Robot.
 *  
 *  Methods:
 *   - executeActions: executes the instruction of the selected robot.
 *      - Arguments: 
 *          - Factory: matrix cells within the Robot moves.
 *          - Robot: object whose actions are predefined.
 *      - Return value: none
*/

@Component
public class RobotCommander {
    public void executeActions(Factory factory, Robot robot) {
        List<Command> commands = robot.getActionChart();
        int currentAction = robot.getCurrentActionIndex();

        while (currentAction < commands.size()) {
            Command command = commands.get(currentAction);
            boolean moved = true;

            switch (command) {
                // case L(left rotation)
                case L -> robot.turnLeft();
                // case R(right rotation)
                case R -> robot.turnRight();
                // case M(move forward)
                case M -> {
                    // check if robot has moved or has been blocked by another robot
                    moved = factory.moveRobot(robot);
                    if (!moved) {
                        robot.setCurrentActionIndex(currentAction);
                        return;
                    }
                }
            }
            if (!moved)
                break;
            robot.setCurrentActionIndex(++currentAction);
        }
    }
}
