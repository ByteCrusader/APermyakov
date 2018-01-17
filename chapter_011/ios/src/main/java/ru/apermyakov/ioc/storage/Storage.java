package ru.apermyakov.ioc.storage;

import ru.apermyakov.ioc.user.User;

import java.util.Map;

/**
 * Interface for storage.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
public interface Storage {

    /**
     * Method for add user.
     *
     * @param user user.
     */
    void add(User user);

    /**
     * Method for get all users.
     *
     * @return map of users.
     */
    Map<String, User> getUsers();

    /**
     * Method for get user by name.
     *
     * @param userName user name.
     * @return user.
     */
    User getUserByName(String userName);
}
