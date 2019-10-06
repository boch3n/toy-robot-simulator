package utils;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class UtilsTest {

    @Test
    public void verifyCommand_wrongCommand_shouldFail(){
        //given
        String wrongCommand = "MOOVE";

        //when
        boolean result = Utils.verifyCommand(wrongCommand);

        //then
        assertFalse(result);
    }

    @Test
    public void verifyCommand_noCommand_shouldFail(){
        //given
        String wrongCommand = "";

        //when
        boolean result = Utils.verifyCommand(wrongCommand);

        //then
        assertFalse(result);
    }

    @Test
    public void verifyCommand_goodCommand_shouldSucceed(){
        //given
        String wrongCommand = "report";

        //when
        boolean result = Utils.verifyCommand(wrongCommand);

        //then
        assertTrue(result);
    }

    @Test
    public void verifyPlaceCommand_shouldSucceed(){
        //given
        String command = "PLACE 2,3,SOUTH";

        //when
        boolean result = Utils.verifyPlaceCommand(command);

        //then
        assertFalse(result);
    }
}
