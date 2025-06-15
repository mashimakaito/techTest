package com.technicalinterview.technical_interview;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.technicalinterview.technical_interview.infrastructure.adapter.in.FactoryDataValidator;

// Class to unit test the Validator In-bound Adapter
public class FactoryDataValidatorTest {

    // Unit Test 1: Sample input case
    @Test
    public void testValidInput() {
        String validInput = "5 5\n" +
                "1 2 N\n" +
                "LMLMLMLMM\n" +
                "3 3 E\n" +
                "MMRMMRMRRM";

        assertDoesNotThrow(() -> FactoryDataValidator.validate(validInput));
    }

    // Unit Test 2: Missing matrix initialitazion parameters
    @Test
    public void testMissingCharactersInput() {
        String validInput = "5\n" +
                "1 2 N\n" +
                "LMLMLMLMM\n" +
                "3 3\n" +
                "MMRMMRMRRM";

        assertThrows(Exception.class, () -> FactoryDataValidator.validate(validInput));
        ;
    }

    // Unit Test 3: Adding extra characters to matrix initialization and robot
    // position initialization lines
    @Test
    public void testMoreCharactersInput() {
        String validInput = "5 5 4\n" +
                "1 2 N S\n" +
                "LMLMLMLMM\n" +
                "3 3 E O\n" +
                "MMRMMRMRRM";

        assertThrows(Exception.class, () -> FactoryDataValidator.validate(validInput));
    }

    // Unit Test 4: Removing command instructions lines
    @Test
    public void testMissingCommandsInput() {
        String validInput = "5 5 4\n" +
                "1 2 N S\n" +
                "3 3 E O\n";

        assertThrows(Exception.class, () -> FactoryDataValidator.validate(validInput));
    }

    @Test
    public void testNRobotsInput() {
        String validInput = "5 5\n" +
                "1 2 N\n" +
                "LMLMLMLMM\n" +
                "3 3 E\n" +
                "MMRMMRMRRM\n" +
                "2 0 N\n" +
                "MRRMLMM";

        assertDoesNotThrow(() -> FactoryDataValidator.validate(validInput));
    }
}
