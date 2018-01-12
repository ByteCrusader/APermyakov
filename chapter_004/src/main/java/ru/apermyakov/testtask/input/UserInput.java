package ru.apermyakov.testtask.input;

import ru.apermyakov.testtask.output.Output;
import ru.apermyakov.testtask.output.UserOutput;

import java.util.Scanner;

/**
 * Class for modulate user input.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public class UserInput implements Input {

    /**
     * Field for user input scanner.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Field for user output.
     */
    private Output output = new UserOutput();

    /**
     * Method for ask user.
     *
     * @param message message.
     * @return user answer.
     */
    @Override
    public String ask(String message) {
        output.sendMessage(message);
        return scanner.nextLine();
    }
}
