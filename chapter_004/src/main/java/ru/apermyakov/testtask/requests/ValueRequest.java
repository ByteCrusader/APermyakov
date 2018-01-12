package ru.apermyakov.testtask.requests;

import ru.apermyakov.testtask.user.User;

import java.util.List;

/**
 * Interface for value request.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public interface ValueRequest {

    /**
     * Method for request value.
     *
     * @param users list of users.
     */
    void requestValue(List<User> users);
}
