package ru.apermyakov.ioc.picker;

import ru.apermyakov.ioc.storage.Storage;
import ru.apermyakov.ioc.user.User;

import java.util.Map;

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

    /**
     * Method for get all users.
     *
     * @return map of users.
     */
    @Override
    public Map<String, User> getUsers() {
        return this.storage.getUsers();
    }

    /**
     * Method for get user by name.
     *
     * @param userName user name.
     * @return user.
     */
    @Override
    public User getUserByName(String userName) {
        return this.storage.getUserByName(userName);
    }
}
