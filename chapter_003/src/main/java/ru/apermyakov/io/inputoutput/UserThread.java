package ru.apermyakov.io.inputoutput;

import java.io.File;
import java.util.Scanner;

/**
 * Class for modulate user thread.
 *
 * @author apermyakov
 * @version 1.0
 * @since 25.12.2017
 */
public class UserThread extends ChatThread {

    /**
     * Design user thread.
     *
     * @param log log file
     */
    public UserThread(File log) {
        super(log);
    }

    /**
     * Method for check user input.
     *
     * @param userInput user input
     * @param result result
     * @return result
     */
    private boolean checkInput(String userInput, boolean result) {
        final String end = "закончить";
        final String stop = "стоп";
        final String cont = "продолжить";
        if (end.equals(userInput)) {
            Thread.currentThread().interrupt();
            result = false;
        }
        if (stop.equals(userInput)) {
            result = false;
        }
        if (cont.equals(userInput)) {
            result = true;
        }
        return result;
    }

    /**
     * Method for modulate user thread main activity.
     */
    @Override
    public void run() {
        boolean result = true;
        while (!Thread.currentThread().isInterrupted()) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNext()) {
                String userInput = scanner.nextLine();
                synchronized (log) {
                    super.logToTxt(userInput);
                    if (result = checkInput(userInput, result)) {
                        log.notify();
                    }
                }
            }
        }
    }
}
