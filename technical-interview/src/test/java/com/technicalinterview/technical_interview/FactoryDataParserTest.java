package com.technicalinterview.technical_interview;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.technicalinterview.technical_interview.domain.model.Command;
import com.technicalinterview.technical_interview.domain.model.Factory;
import com.technicalinterview.technical_interview.domain.model.Orientation;
import com.technicalinterview.technical_interview.domain.model.Position;
import com.technicalinterview.technical_interview.domain.model.Robot;
import com.technicalinterview.technical_interview.domain.service.FactoryDataParser;

public class FactoryDataParserTest {

    private final FactoryDataParser factoryParser = new FactoryDataParser();

    @Test
    void createFactoryRoutesService_testRobotInitialize() throws Exception {
        Position positionR1 = new Position(1, 2);
        Robot robot1 = new Robot(positionR1, Orientation.N, 1);
        List<Command> commandR1 = List.of(Command.L, Command.M, Command.L, Command.M, Command.L, Command.M, Command.L, Command.M, Command.M);
        robot1.setActionChart(commandR1);

        Position positionR2 = new Position(3, 3);
        Robot robot2 = new Robot(positionR2, Orientation.E, 2);
        List<Command> commandR2 = List.of(Command.M, Command.M, Command.R, Command.M, Command.M, Command.R, Command.M, Command.R, Command.R, Command.M);
        robot2.setActionChart(commandR2);

        List<Robot> robotList = List.of(robot1, robot2);

        String inputValue = "5 5\n" +
                "1 2 N\n" +
                "LMLMLMLMM\n" +
                "3 3 E\n" +
                "MMRMMRMRRM";

        Factory result = factoryParser.parse(inputValue);
        List<Robot> expected = new ArrayList<>(result.getRobots());
        assertThat(robotList).containsExactlyInAnyOrderElementsOf(expected);
    }

}
