package com.technicalinterview.technical_interview.infrastructure.adapter.in;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technicalinterview.technical_interview.infrastructure.port.in.CreateFactoryRoutesUseCase;

/*
 * API HTTP Layer implementation:
 *  Created in order to test the connection with the application using HTTP protocols.
*/

@RestController
@RequestMapping
public class FactoryController {
    private final CreateFactoryRoutesUseCase createFactoryRoutesUseCase;

    public FactoryController(CreateFactoryRoutesUseCase createFactoryRoutesUseCase) {
        this.createFactoryRoutesUseCase = createFactoryRoutesUseCase;
    }

    @PostMapping
    public String createFactoryRoutes(@RequestBody String inputStr) throws Exception {
        FactoryDataValidator.validate(inputStr);
        return createFactoryRoutesUseCase.createRoutes(inputStr);
    }
}
