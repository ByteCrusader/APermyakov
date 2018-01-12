package ru.apermyakov.testtask.output;

/**
 * Interface for output message.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public interface OutputMessage {

    /**
     * Method for send message.
     *
     * @param message message.
     */
    void sendMessage(String message);
}
