![image](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEYXuuAFa71aZb9oQoRhSZBsebRHlYXlecQw&s)
# Volkswage Digital:Hub Technical Challenge

## Author
**Name:** Jose Francisco Aguilera Oliver\
**Email:** josefranciscao18@gmail.com

---

## Technologies Used

- **Language:** Java 21
- **Frameworks:** SpringBoot
- **Build Tool:** Maven
- **Tools:** Postman
- **Testing:** JUnit

---

## Technical decisions

### Architecture & Design
I applied an Hexagonal Arquitecture, using Domain Driven Design principles, to promote a clean separation between the core business logic and the external data from other sources. The arquitecture used in this challenge is conformed by:

    | Application    | Domain  | Infrastructure |
    | -----------    |:-------:|:--------------:|
    | Service        | Model   | Port(in)       |
    | Domain         | Service | Adapter(in)    |
    | Infrastructure | -       | -              |


 * Infrastructure: manages external communication and system-level concerns. In the context of this challenge, I introduced the infrastructure layer to handle the filtering of input string data. This separation promotes cleaner architecture by isolating rolow-level operations fm business logic. Additionally, it enhances the application's scalability and maintainability due to the fact that future integrations, such as I/O operations, database connections, or third-party services, can be added.
    * Port: as explained before, the implemented ports in this process were just inbound ports responsible for declaring and managing the use-cases of the application.
    * Adapter: inbound adapters which responsibility is to apply filtering processes to the required input string data (for example, filtering the format).

 * Application: this layer orchestrates the core use cases of the system. It implements the interface methods defined by the inbound ports, which describe the business actions available to the outside world. In this challenge, the application layer coordinates the domain level logic to return the required values.
    * Service: each use case is divided in one specific service which is responsible of doing the described process. In this challenge I only cover one service which corresponds to the Factory robot routes creation process.

 * Domain: encapsulates all the core business logic and rules that govern the behavior of the application.
    * Model: core business entities and value objects. These models encapsulate both state and behavior.
    * Service: abstraction layer that orquestrates all the processes from the models in order to improve separation of concerns. Contain business logic that doesn't naturally fit within a single model.


### Frameworks
- I used Spring Boot to add HTTPS support in order to test secure communication with the application. While it was not explicitly required in the challenge, I included it to simulate a production-ready environment where secure data transmission is essential.
This also demonstrates how the application can be extended with minimal configuration to meet security standards.

---

## Assumptions Made

- I assumed that, even if it was specified that there was any obstacles, the robots that were still, waiting for their sequence of instructions, could become an obstacle for the ones in motion. 
    - The solution I applied was to stop the current motion of the robot and save the cancelled instruction. After that, the next robot in sequence starts and follows the same pattern of action if the case repeats. Finally, after every robot finishes or stops its motion, the paused robots start to move again in the order that they were supposed to move at first. The process does not stop till all the robots finish or there is just one robot left with no possible moves remaining.

- I assumed that I had to apply an HTTP layer to demonstrate my backend development skills, even though it wasn’t explicitly required in the challenge. By adding this layer, I was able to expose the application’s core use cases through a REST API, enabling interaction and testing via standard HTTP tools like Postman or curl. Furthermore, it reflects how the application would behave in a real-world scenario, where external clients would communicate with the system over connection protocols.

---

## How to run

```bash
# Clone the repository
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name

# Run the project
./mvnw spring-boot:run
