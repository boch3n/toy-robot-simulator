package game;

import enums.Direction;
import enums.Request;

import java.util.Arrays;

import static enums.Direction.*;

public class ToyRobot {

    private static ToyRobot instance;
    private int positionX;
    private int positionY;
    private Direction facing;

    private ToyRobot() {
    }

    public static ToyRobot getInstance() {
        if (instance == null) {
            instance = new ToyRobot();
        }
        return instance;
    }

    public void runCommand(String input) {

        if (input.toUpperCase().contains(Request.PLACE.name()))
            place(input);
        else if (input.equalsIgnoreCase(Request.MOVE.name()))
            move();
        else if (input.equalsIgnoreCase(Request.LEFT.name()))
            left();
        else if (input.equalsIgnoreCase(Request.RIGHT.name()))
            right();
        else
            report();
    }

    private void place(String input) {
        setPositionX(Integer.valueOf(input.substring(6, 7)));
        setPositionY(Integer.valueOf(input.substring(8, 9)));
        setFacing(Direction.valueOf(getFacing(input)));
    }

    private String getFacing(String userInput) {
        if (userInput.charAt(10) == 'N' || userInput.charAt(10) == 'S') {
            return userInput.substring(10, 15);
        } else
            return userInput.substring(10, 14);
    }

    private void move() {
        switch (getFacing()) {
        case NORTH:
            incrementPositionY();
            break;
        case SOUTH:
            decrementPositionY();
            break;
        case WEST:
            decrementPositionX();
            break;
        case EAST:
            incrementPositionX();
            break;
        }
    }

    private void left() {
        switch (getFacing()) {
        case NORTH:
            setFacing(WEST);
            break;
        case SOUTH:
            setFacing(EAST);
            break;
        case WEST:
            setFacing(SOUTH);
            break;
        case EAST:
            setFacing(NORTH);
            break;
        }
    }

    private void right() {
        switch (getFacing()) {
        case NORTH:
            setFacing(EAST);
            break;
        case SOUTH:
            setFacing(WEST);
            break;
        case WEST:
            setFacing(NORTH);
            break;
        case EAST:
            setFacing(SOUTH);
            break;
        }
    }

    private void report() {
        System.out
                .println("Toy robot is on position(" + getPositionX() + "," + getPositionY() + "," + getFacing() + ")");
    }

    public int getPositionX() {
        return positionX;
    }

    private void setPositionX(int positionX) {
        if (positionX < 0 || positionX >= 5) {
            System.out.println("ERROR! Toy Robot can't be outside the tabletop.");
        } else {
            this.positionX = positionX;
        }
    }

    private void incrementPositionX() {
        this.positionX++;
    }

    private void decrementPositionX() {
        this.positionX--;
    }

    public int getPositionY() {
        return positionY;
    }

    private void setPositionY(int positionY) {
        if (positionY < 0 || positionY >= 5) {
            System.out.println("ERROR! Toy Robot can't be outside the tabletop.");
        } else {
            this.positionY = positionY;
        }
    }

    private void incrementPositionY() {
        this.positionY++;
    }

    private void decrementPositionY() {
        this.positionY--;
    }

    public Direction getFacing() {
        return facing;
    }

    private void setFacing(Direction facing) {
        if (!Arrays.asList(Direction.values()).contains(facing)) {
            System.out.println("ERROR! You can't set this facing.");
        } else {
            this.facing = facing;
        }
    }
}
