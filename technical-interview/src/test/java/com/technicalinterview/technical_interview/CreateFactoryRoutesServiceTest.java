package com.technicalinterview.technical_interview;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.technicalinterview.technical_interview.application.service.CreateFactoryRoutesService;

@SpringBootTest
public class CreateFactoryRoutesServiceTest {
    
    @Autowired
    private CreateFactoryRoutesService createFactoryRoutesService;

    // Normal test case: sample input
    @Test
    void createRoutes_returnsSampleExpectedResult() throws Exception {
        String input = "5 5\n" +
                "1 2 N\n" +
                "LMLMLMLMM\n" +
                "3 3 E\n" +
                "MMRMMRMRRM";

        String expectedOutput = "1 3 N\n" +
            "5 1 E\n";

        String result = createFactoryRoutesService.createRoutes(input);

        assertEquals(expectedOutput, result);
    }

    // Alternative test case: Colliding robots test
    @Test
    void createRoutes_returnsAltExpectedResult() throws Exception {
        String input = "5 5\n" +
                "1 2 E\n" +
                "MMLMMRMMM\n" +
                "3 3 S\n" +
                "RMLMLMMRRM";


        String expectedOutput = "3 2 N\n" +
            "2 2 E\n";

        String result = createFactoryRoutesService.createRoutes(input);

        assertEquals(expectedOutput, result);
    }
}
