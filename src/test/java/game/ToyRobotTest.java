package game;

import enums.Direction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class ToyRobotTest {

    @InjectMocks
    ToyRobot toyRobotMock;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void runCommnad_placeCommand_shouldSucceed() {
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
    public void runCommnad_placeCommand_shouldFail() {
        //given
        String userInput = "PLACE 5,6,EAST";

        //when
        toyRobotMock.runCommand(userInput);

        //then
        assertEquals(0, toyRobotMock.getPositionX());
        assertEquals(0, toyRobotMock.getPositionY());
        assertEquals(Direction.EAST, toyRobotMock.getFacing());
    }
}
