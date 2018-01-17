package ru.apermyakov.ioc.picker;

import ru.apermyakov.ioc.user.User;

/**
 * Interface for picker.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
public interface Picker {

    /**
     * Method for add user.
     *
     * @param user user.
     */
    void add(User user);
}
