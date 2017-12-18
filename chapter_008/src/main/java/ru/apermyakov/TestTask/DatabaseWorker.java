package ru.apermyakov.TestTask;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for build main actions with database.
 *
 * @author apermyakov
 * @version 1.0
 * @since 05.12.2017
 */
public class DatabaseWorker {

    /**
     * Field for log4j logger.
     */
    private static final Logger logger = Logger.getLogger(DatabaseWorker.class);

    /**
     * Field for map of scripts.
     */
    private Map<String, String> scripts = new HashMap<>();

    /**
     * Design database worker.
     *
     * @param scripts map of scripts
     */
    DatabaseWorker(Map<String, String> scripts) {
        this.scripts = scripts;
    }

    /**
     * Method to build connection to database.
     *
     * @return connection object
     * @throws SQLException sql e
     */
    private Connection connect() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql:" + this.scripts.get("connect"), this.scripts.get("login"), this.scripts.get("password"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Method for initial vacancy and renewal tables.
     *
     * @param connection connection
     * @return true if success
     */
    private boolean initialTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(this.scripts.get("createvacancy"));
            statement.executeUpdate(this.scripts.get("createrenewal"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Method for add vacancy to database if not exist.
     *
     * @param connection connection object
     * @param name       name of vacancy
     * @param href       href of vacancy
     * @param data       data of vacancy
     */
    private void addToBase(Connection connection, String name, String href, String data) {
        try {
            NamedParameterStatement statement = new NamedParameterStatement(connection, this.scripts.get("insertvacancy"));
            statement.setString("name", name);
            statement.setString("href", href);
            statement.setString("data", data);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for end record to database.
     *
     * @param connection connection object
     * @return true if success
     */
    private boolean endRecord(Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(this.scripts.get("insertrenewal"))) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            statement.setString(1, dateFormat.format(calendar.getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Method for check renewal to first start or not.
     *
     * @param connection connection object
     * @return false if first else false
     */
    private boolean checkRenewal(Connection connection) {
        boolean result = false;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(this.scripts.get("selectrenewal"));
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method for select results from database.
     *
     * @param connection connection object
     * @return true if success
     */
    private boolean results(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(this.scripts.get("selectvacancy"));
            while (resultSet.next()) {
                logger.log(Level.INFO, (String.format("%s %s %s", resultSet.getString("vacancyname"), resultSet.getString("vacancyhref"), resultSet.getString("vacancydata"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Method for main activity of database worker.
     *
     * @param item chose item.
     * @return method's result
     */
    public boolean mainActivity(String item) {
        final String init = "initial";
        final String end = "end";
        final String check = "check";
        final String res = "result";
        Connection connection = null;
        boolean result = false;
        try {
            connection = this.connect();
            if (init.equals(item)) {
                result = this.initialTable(connection);
            }
            if (end.equals(item)) {
                result = this.endRecord(connection);
            }
            if (check.equals(item)) {
                result = this.checkRenewal(connection);
            }
            if (res.equals(item)) {
                result = this.results(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * Overload main activity for add vacancy to database.
     *
     * @param item item
     * @param name vacancy's name
     * @param href vacancy's href
     * @param data vacancy's data
     */
    public void mainActivity(String item, String name, String href, String data) {
        final String add = "add";
        Connection connection = null;
        try {
            connection = this.connect();
            if (add.equals(item)) {
                this.addToBase(connection, name, href, data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}