package ru.apermyakov.ioc.storage;

import org.springframework.stereotype.Component;
import ru.apermyakov.ioc.user.SimpleUser;
import ru.apermyakov.ioc.user.User;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for annotation storage.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
@Component
public class JdbcStorage implements DbStorage {

    private Map<String, String> connSettings = new HashMap<>();

    /**
     * Method to build connection to database.
     *
     * @return connection object
     * @throws SQLException sql e
     */
    private Connection connect() throws SQLException {
        Connection conn = null;
        try {
            Class.forName(this.connSettings.get("driver"));
            conn = DriverManager.getConnection(this.connSettings.get("url"), this.connSettings.get("userName"), this.connSettings.get("userPass"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Method for add user.
     *
     * @param user user.
     */
    @Override
    public void add(User user) {
        try (Connection connection = this.connect();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO USERS (NAME) VALUES (?)")) {
            statement.setString(1, user.getName());
            statement.executeUpdate();
            System.out.format("Add %s to DB%s", user.getName(), System.lineSeparator());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for get all users.
     *
     * @return map of users.
     */
    @Override
    public Map<String, User> getUsers() {
        Map<String, User> userMap = new HashMap<>();
        try (Connection connection = this.connect();
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM USERS");
            while(result.next()) {
                String userName = result.getString("name");
                User user = new SimpleUser();
                user.setName(userName);
                userMap.put(userName, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userMap;
    }

    /**
     * Method for get user by name.
     *
     * @param userName user name.
     * @return user.
     */
    @Override
    public User getUserByName(String userName) {
        User resultUser = new SimpleUser();
        try (Connection connection = this.connect();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE USERS.NAME=?")) {
            statement.setString(1, userName);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                resultUser.setName(result.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultUser;
    }

    /**
     * Method for make connection settings.
     *
     * @param connSettings connection settings.
     */
    @Override
    public void makeConnSettings(Map<String, String> connSettings) {
        this.connSettings = connSettings;
    }
}
