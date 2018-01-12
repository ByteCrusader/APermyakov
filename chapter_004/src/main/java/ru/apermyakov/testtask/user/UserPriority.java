package ru.apermyakov.testtask.user;

/**
 * Interface for user priority.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public interface UserPriority {

    /**
     * Method for set user priority.
     *
     * @param priority priority.
     */
    void setPriority(int priority);

    /**
     * Method for get user priority.
     *
     * @return priority.
     */
    int getPriority();
}
