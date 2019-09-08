# ToyRobotChallenge
The code is based on the challenge specified at <https://zone.github.io/backend/toy-robot>

The challenge has been implemented in java using TDD approach. Unit tests were written for most of the commands and then the implementation done and verified by running the unit tests.

The solution has been designed with zero external dependencies except for the tests, which used junit5 test harness and hamcrest library for assertions. 

The project is a gradle project and to build it run

`./gradlew clean build`

##Assumptions
Certain assumptions have been made when designing the solution. They are
* Because the robot should not destroy itself due to any operation that moves it off the grid, any such operation is termed as invalid and the robot remains in the same position as it was before the operation.
* The implementation has been done with it being a service in mind and any output which in this case is from the report command, will provide the response in JSON format. The report command will return the current state of the robot as a JSON response instead of printing it. It is upto the caller to use it in whatever it way it deems necessary.
 
## Reference application
A reference application which is a client of the implemented ToyRobotService issues commands against the service based on a commands defined in a file. This application is run against the testcase files defined in the resources directory. In this application we print the repose given by the report command to standard output.

To run the reference application from the project home directory execute the command

`java -cp build/classes/java/main:build/classes/java/test:build/resources/test interview.test.zone.toyrobot.ToyRobotApplication`


 