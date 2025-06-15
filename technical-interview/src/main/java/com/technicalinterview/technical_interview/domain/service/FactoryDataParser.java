package com.technicalinterview.technical_interview.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.technicalinterview.technical_interview.domain.model.Command;
import com.technicalinterview.technical_interview.domain.model.Factory;
import com.technicalinterview.technical_interview.domain.model.Orientation;
import com.technicalinterview.technical_interview.domain.model.Position;
import com.technicalinterview.technical_interview.domain.model.Robot;

/*
 * FactoryDataParser implementation:
 *  Domain service that uses the input string format to initialize all the domain model instances.
 * 
 *  Methods: 
 *   - parse: as the name implies, parses all the input filtered string data and creates all the
 *      necessary components for the process.
 *      - Arguments: string - specific and filtered format string that contains all relevant data 
 *         to initialize the Factory and Robot values.
 *      - Return value: Factory
 * 
*/

@Component
public class FactoryDataParser {
    public Factory parse(String inputStr) throws Exception {
        Scanner input = new Scanner(inputStr);

        String matrixDataLine = input.nextLine();
        String[] matrixMaxPos = matrixDataLine.split(" ");
        int width = Integer.parseInt(matrixMaxPos[0]);
        int height = Integer.parseInt(matrixMaxPos[1]);
        Factory factoryInstance = new Factory(width, height);

        try {
            int orderId = 0;
            while (input.hasNextLine()) {
                ++orderId;
                String robotPosDataLine = input.nextLine();
                String[] robotInitPos = robotPosDataLine.split(" ");
                int x = Integer.parseInt(robotInitPos[0]);
                int y = Integer.parseInt(robotInitPos[1]);
                Position robotPos = new Position(x, y);
                Orientation robotOrientation = Orientation.valueOf(robotInitPos[2].toUpperCase());
                Robot robotInstance = new Robot(robotPos, robotOrientation, orderId);
                factoryInstance.placeRobot(robotPos, robotInstance);
                if (input.hasNextLine()) {
                    String robotInstructionsLine = input.nextLine();
                    List<Command> instructionList = new ArrayList<>();
                    for (char c : robotInstructionsLine.toCharArray()) {
                        instructionList.add(Command.valueOf(String.valueOf(c).toUpperCase()));
                    }
                    robotInstance.setActionChart(instructionList);
                }
            }
            return factoryInstance;
        } catch (Exception e) {
            throw new Exception("Invalid input data.");
        } finally {
            input.close();
        }
    }
}
