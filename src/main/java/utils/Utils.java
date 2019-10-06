package utils;

import enums.Request;

import java.util.Arrays;

public class Utils {

    public static boolean verifyCommand(String inputCommand) {
        return Arrays.stream(Request.values()).anyMatch(cmd -> inputCommand.toUpperCase().contains(cmd.name()));
    }

}
