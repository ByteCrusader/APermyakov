package ru.apermyakov.io.inputoutput;

import java.io.File;

/**
 * Main class for console chat.
 *
 * @author apermyakov
 * @version 1.0
 * @since 25.12.2017
 */
public class ConsoleChat {

    /**
     * Method for initial chat.
     */
    public void startChat() {
        File log = new File("C:\\chat\\log.txt");
        UserThread user = new UserThread(log);
        ConsoleThread console = new ConsoleThread(log);
        Thread userThread = new Thread(user);
        userThread.start();

        Thread consoleThread = new Thread(console);
        consoleThread.setDaemon(true);
        consoleThread.start();
    }

    /**
     * Method for check multithread work.
     *
     * @param args args
     */
    public static void main(String[] args) {
        ConsoleChat chat = new ConsoleChat();
        chat.startChat();
    }
}
