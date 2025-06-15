package com.technicalinterview.technical_interview.infrastructure.adapter.in;

import java.util.Scanner;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

/*
 * FactoryDataValidator implementation:
 *  Inbound adapter that checks the string format before it arrives to the application use cases.
 *  Valid format implies:
 *      First line: matrix up-right corner coordinates
 *      Second line*: robot initial position
 *      Third line*: robot instructions
 *  
 *  *Second and third lines can be repeated n times, depending on the number of required robots.
*/

@Component
public class FactoryDataValidator {
    private static final Pattern MATRIX_LINE_PATTERN = Pattern.compile("^\\d+ \\d+$");
    private static final Pattern ROBOT_POSITION_PATTERN = Pattern.compile("^\\d+ \\d+ [NSEW]$");
    private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("^[LRM]+$");

    public static void validate(String input) throws RuntimeException {
        Scanner scanner = new Scanner(input);

        try {
            if (!scanner.hasNextLine()) {
                throw new RuntimeException("Missing matrix size line");
            }

            String matrixLine = scanner.nextLine().trim();
            if (!MATRIX_LINE_PATTERN.matcher(matrixLine).matches()) {
                throw new RuntimeException("Invalid matrix line: " + matrixLine);
            }

            int robotCount = 0;
            while (scanner.hasNextLine()) {
                String positionLine = scanner.nextLine().trim();
                if (!ROBOT_POSITION_PATTERN.matcher(positionLine).matches()) {
                    throw new RuntimeException("Invalid robot position line: " + positionLine);
                }

                if (!scanner.hasNextLine()) {
                    throw new RuntimeException("Missing instruction line for robot at: " + positionLine);
                }

                String instructionLine = scanner.nextLine().trim();
                if (!INSTRUCTION_PATTERN.matcher(instructionLine).matches()) {
                    throw new RuntimeException("Invalid instruction line: " + instructionLine);
                }

                robotCount++;
            }

            if (robotCount == 0) {
                throw new RuntimeException("No robots defined in input");
            }

        } finally {
            scanner.close();
        }
    }
}
