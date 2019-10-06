package game;

import enums.Direction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class ToyRobotTest {

    @InjectMocks
    ToyRobot toyRobotMock;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setup() {
        initMocks(this);
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void runCommand_placeCommand_shouldSucceed() {
        //given
        String userInput = "PLACE 2,3,EAST";

        //when
        toyRobotMock.runCommand(userInput);

        //then
        assertEquals(2, toyRobotMock.getPositionX());
        assertEquals(3, toyRobotMock.getPositionY());
        assertEquals(Direction.EAST, toyRobotMock.getFacing());
    }

    @Test
    public void runCommand_placeCommand_shouldFail() {
        //given
        String userInput = "PLACE 5,6,EAST";

        //when
        toyRobotMock.runCommand(userInput);

        //then
        assertEquals(0, toyRobotMock.getPositionX());
        assertEquals(0, toyRobotMock.getPositionY());
        assertEquals(Direction.EAST, toyRobotMock.getFacing());
    }

    @Test
    public void runCommand_leftCommand_shouldSucceed() {
        //given
        String initializeInput = "PLACE 1,1,EAST";
        String userInput = "LEFT";

        //when
        toyRobotMock.runCommand(initializeInput);
        toyRobotMock.runCommand(userInput);

        //then
        assertEquals(Direction.NORTH, toyRobotMock.getFacing());
    }

    @Test
    public void runCommand_rightCommand_shouldSucceed() {
        //given
        String initializeInput = "PLACE 1,1,EAST";
        String userInput = "right";

        //when
        toyRobotMock.runCommand(initializeInput);
        toyRobotMock.runCommand(userInput);

        //then
        assertEquals(Direction.SOUTH, toyRobotMock.getFacing());
    }

    @Test
    public void runCommand_moveSouthCommand_shouldSucceed() {
        //given
        String initializeInput = "PLACE 1,1,SOUTH";
        String userInput = "move";

        //when
        toyRobotMock.runCommand(initializeInput);
        toyRobotMock.runCommand(userInput);

        //then
        assertEquals(1, toyRobotMock.getPositionX());
        assertEquals(0, toyRobotMock.getPositionY());
        assertEquals(Direction.SOUTH, toyRobotMock.getFacing());
    }

    @Test
    public void runCommand_moveNorthCommand_shouldSucceed() {
        //given
        String initializeInput = "PLACE 2,4,NORTH";
        String userInput = "move";

        //when
        toyRobotMock.runCommand(initializeInput);
        toyRobotMock.runCommand(userInput);

        //then
        assertEquals(2, toyRobotMock.getPositionX());
        assertEquals(4, toyRobotMock.getPositionY());
        assertEquals(Direction.NORTH, toyRobotMock.getFacing());

        String result = outContent.toString().trim();
        String expectedResult = "WARNING! Toy robot decided to ignore command.";

        assertEquals(expectedResult, result);
    }

    @Test
    public void runCommand_moveEastCommand_shouldSucceed() {
        //given
        String initializeInput = "PLACE 0,0,EAST";
        String userInput = "move";

        //when
        toyRobotMock.runCommand(initializeInput);
        toyRobotMock.runCommand(userInput);

        //then
        assertEquals(1, toyRobotMock.getPositionX());
        assertEquals(0, toyRobotMock.getPositionY());
        assertEquals(Direction.EAST, toyRobotMock.getFacing());
    }

    @Test
    public void runCommand_moveWestCommand_shouldSucceed() {
        //given
        String initializeInput = "PLACE 0,0,WEST";
        String userInput = "move";

        //when
        toyRobotMock.runCommand(initializeInput);
        toyRobotMock.runCommand(userInput);

        //then
        assertEquals(0, toyRobotMock.getPositionX());
        assertEquals(0, toyRobotMock.getPositionY());
        assertEquals(Direction.WEST, toyRobotMock.getFacing());

        String result = outContent.toString().trim();
        String expectedResult = "WARNING! Toy robot decided to ignore command.";

        assertEquals(expectedResult, result);
    }

    @Test
    public void runCommand_reportCommand_shouldSucceed() {
        //given
        String initializeInput = "PLACE 1,1,SOUTH";
        String userInput = "move";
        String reportInput = "report";

        //when
        toyRobotMock.runCommand(initializeInput);
        toyRobotMock.runCommand(userInput);
        toyRobotMock.runCommand(reportInput);

        String result = outContent.toString().trim();
        String expectedResult = "Toy robot is on position 1,0,SOUTH.";

        //then
        assertEquals(expectedResult, result);
    }
}
