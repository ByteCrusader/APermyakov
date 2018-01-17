package ru.apermyakov.ioc.picker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.apermyakov.ioc.storage.DbStorage;
import ru.apermyakov.ioc.user.User;

import java.util.Map;

/**
 * Class for demonstrate annotation spring.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
@Component
public class JdbcPicker implements DbPicker {

    /**
     * Field for annotation storage.
     */
    private final DbStorage storage;

    @Autowired
    public JdbcPicker(DbStorage storage) {
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

    /**
     * Method for make connection settings.
     *
     * @param connSettings connection settings.
     */
    @Override
    public void makeConnSettings(Map<String, String> connSettings) {
        this.storage.makeConnSettings(connSettings);
    }
}
