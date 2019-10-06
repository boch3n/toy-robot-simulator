package home;

import game.ToyRobot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utils.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(Application.class);

    private Scanner reader = new Scanner(System.in);

    private ToyRobot toyRobot = ToyRobot.getInstance();

    private static final String FILE_URL = "C:\\Toy-robot-simulator\\toy-robot-simulator.txt";

    public static void main(String[] args) {
        LOG.info("STARTING TOY ROBOT SIMULATOR");
        SpringApplication.run(Application.class, args);
        LOG.info("TOY ROBOT SIMULATOR FINISHED");
    }

    @Override
    public void run(String... args) {
        System.out.println("\n\t\t Welcome to Toy Robot simulator!\n");

        try (Stream<String> stream = Files.lines(Paths.get(FILE_URL))) {
            System.out.println("File found! List of commnands executed:");
            List<String> commands = stream.collect(Collectors.toList());
            commands.forEach(System.out::println);

            for (String command : commands) {
                toyRobot.runCommand(command);
            }

        } catch (IOException e) {
            System.out.println("NO FILE! Type commands manually.");
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
        }

        System.out.println("\n\t\tThank you! Have a nice day.\n");
    }
}