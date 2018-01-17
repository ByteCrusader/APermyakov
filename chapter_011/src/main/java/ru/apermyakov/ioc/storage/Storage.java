package ru.apermyakov.ioc.storage;

import ru.apermyakov.ioc.user.User;

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
}
