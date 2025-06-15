package com.technicalinterview.technical_interview.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import com.technicalinterview.technical_interview.domain.model.Robot;
import com.technicalinterview.technical_interview.domain.model.Factory;
import com.technicalinterview.technical_interview.domain.service.RobotCommander;
import com.technicalinterview.technical_interview.domain.service.FactoryDataParser;
import com.technicalinterview.technical_interview.infrastructure.port.in.CreateFactoryRoutesUseCase;

/*
 * CreateFactoryRoutesService implementation:
 *  Application service that uses all the domain logic methods required to execute the complete
 *  usecase.
 * 
 *  Injections:
 *      - RobotCommander: service that execute the robot actions.
 * 
 *  Methods: 
 *   - createRoutes: method that executes the complete usecase.
 *      - Arguments: string - specific and filtered format string that contains all relevant data 
 *         to initialize the Factory and Robot values.
 *      - Return value: string - required format
 * 
*/

@Service
public class CreateFactoryRoutesService implements CreateFactoryRoutesUseCase {

    private final RobotCommander robotCommander;
    private final FactoryDataParser parser;

    public CreateFactoryRoutesService(RobotCommander robotCommander, FactoryDataParser parser) {
        this.robotCommander = robotCommander;
        this.parser = parser;
    }

    @Override
    public String createRoutes(String inputStr) throws Exception {
        Factory routesMatrix = parser.parse(inputStr);
        Collection<Robot> robots = routesMatrix.getRobots();
        List<Robot> sortedRobotList = robots.stream()
                .sorted(Comparator.comparingInt(Robot::getOrderId))
                .collect(Collectors.toList());
        boolean progressMade;

        do {
            progressMade = false;
            List<Robot> stillBlocked = new ArrayList<>();

            for (Robot robot : sortedRobotList) {
                int initAction = robot.getCurrentActionIndex();
                robotCommander.executeActions(routesMatrix, robot);

                if (robot.getCurrentActionIndex() == initAction) {
                    stillBlocked.add(robot);
                } else {
                    progressMade = true;
                }
            }

            robots = stillBlocked;
        } while (progressMade && !robots.isEmpty());

        return routesMatrix.getRobotsPositionString();
    }
}
