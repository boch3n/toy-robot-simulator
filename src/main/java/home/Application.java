package home;

import game.ToyRobot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utils.Utils;

import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(Application.class);

    private Scanner reader = new Scanner(System.in);

    private ToyRobot toyRobot = ToyRobot.getInstance();

    public static void main(String[] args) {
        LOG.info("STARTING TOY ROBOT SIMULATOR");
        SpringApplication.run(Application.class, args);
        LOG.info("TOY ROBOT SIMULATOR FINISHED");
    }

    @Override
    public void run(String... args) {
        System.out.println("\n\t\t Welcome to Toy Robot simulator!\n");
        System.out.println("To initialize simulator, use command PLACE X,Y,F to place toy robot on the tabletop.");

        while (true) {
            System.out.println("Insert command...");
            String userInput = reader.nextLine();
            if (!Utils.verifyCommand(userInput)) {
                System.out.println("ERROR! You typed wrong command.");
                continue;
            }
            toyRobot.runCommand(userInput);

            System.out.println("Are you finished? (Y/N)");
            userInput = reader.nextLine();
            if (userInput.equalsIgnoreCase("Y") || userInput.equalsIgnoreCase("YES"))
                break;
        }

        System.out.println("\n\t\tThank you! Have a nice day.\n");
    }
}