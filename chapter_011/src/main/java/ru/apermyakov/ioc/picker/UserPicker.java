package ru.apermyakov.ioc.picker;

import ru.apermyakov.ioc.storage.Storage;
import ru.apermyakov.ioc.user.User;

/**
 * Class for demonstrate xml spring.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
public class UserPicker implements Picker {

    /**
     * Field for storage.
     */
    private final Storage storage;

    public UserPicker(Storage storage) {
        this.storage = storage;
    }

    /**
     * Method for add user.
     *
     * @param user user.
     */
    @Override
    public void add(User user) {
        this.storage.add(user);
    }
}
