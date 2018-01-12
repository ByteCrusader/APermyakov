package ru.apermyakov.testtask.output;

/**
 * Class for modulate user output.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public class UserOutput implements Output {

    /**
     * Method for send message.
     *
     * @param message message.
     */
    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }
}
