package ru.apermyakov.testtask.backend;

import ru.apermyakov.testtask.user.User;

/**
 * Interface for backend user work.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public interface BackendUserWork {

    /**
     * Method for work with user.
     *
     * @param user user.
     * @return result of work.
     */
    boolean workWithUser(User user);
}
