package com.technicalinterview.technical_interview.infrastructure.port.in;

/*
 * CreateFactoryRoutesUseCase implementation:
 *  Inbound router that contains the methods used by the application 
 *  use case CreateFactoryRoutes, which purpose is to initialize the Factory matrix cells,
 *  initialize the Robot positions and apply their actions to return their final position 
 *  values.
 * 
 *  Methods: 
 *   - createRoutes: involves all the Factory and Robot actions process
 *      - Return value: string
 * 
*/

public interface CreateFactoryRoutesUseCase {
    String createRoutes(String inputStr) throws Exception;
}
