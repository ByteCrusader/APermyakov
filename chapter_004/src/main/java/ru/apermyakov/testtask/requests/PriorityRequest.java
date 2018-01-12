package ru.apermyakov.testtask.requests;

import ru.apermyakov.testtask.user.User;

import java.util.List;

/**
 * Interface for priority request.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public interface PriorityRequest {

    /**
     * Method for request priority.
     *
     * @param users users.
     */
    void requestPriority(List<User> users);
}
