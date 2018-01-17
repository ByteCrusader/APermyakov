package ru.apermyakov.ioc.storage;

import ru.apermyakov.ioc.user.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for xml storage.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
public class MemoryStorage implements Storage {

    /**
     * Field for storage.
     */
    private Map<String, User> storage = new HashMap<>();

    /**
     * Method for add user.
     *
     * @param user user.
     */
    @Override
    public void add(User user) {
        storage.put(user.getName(), user);
        System.out.format("Add %s to memory%s", user.getName(), System.lineSeparator());
    }

    /**
     * Method for get all users.
     *
     * @return map of users.
     */
    @Override
    public Map<String, User> getUsers() {
        return this.storage;
    }

    /**
     * Method for get user by name.
     *
     * @param userName user name.
     * @return user.
     */
    @Override
    public User getUserByName(String userName) {
        return this.storage.get(userName);
    }
}
