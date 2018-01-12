package ru.apermyakov.testtask.requests;

/**
 * Interface for exit request.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public interface ExitRequest {

    /**
     * Method for request continue or exit.
     *
     * @return true if continue.
     */
    boolean continueGame();
}
