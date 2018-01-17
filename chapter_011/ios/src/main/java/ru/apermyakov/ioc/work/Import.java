package ru.apermyakov.ioc.work;

import ru.apermyakov.ioc.user.User;

/**
 * Interface for import user to db.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
public interface Import {

    /**
     * Method for import user.
     *
     * @param user user.
     */
    void importUser(User user);
}
