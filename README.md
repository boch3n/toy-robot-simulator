# toy-robot-simulator

To run application you have to use command: mvn spring-boot:run

File with example input has to be named simulator-input.txt and be in the folder c://toy-robot-simulator.

Application has 2 paths of execution.
First path, it runs automatically if application is able to find file simulator-input.txt. It outputs all commands in the file, adds comments if boundary conditions are violeted and outputs final result.
Second path, it runs manually if there is no file simulator-input.txt. User is asked about command and later second question appears if he wants to finish or continue. Result will only show when report command run.
