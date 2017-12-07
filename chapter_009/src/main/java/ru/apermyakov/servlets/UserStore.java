package ru.apermyakov.servlets;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

/**
 * Singleton to work with data base.
 *
 * @author apermyakov
 * @version 1.0
 * @since 06.12.2017
 */
@ThreadSafe
public class UserStore {

    /**
     * Field for instance.
     */
    @GuardedBy("this")
    private static final UserStore instance = new UserStore();

    /**
     * Field for connection to data base.
     */
    @GuardedBy("this")
    private static Connection connection;

    static {
        PoolProperties properties = new PoolProperties();
        properties.setUrl("jdbc:postgresql://localhost:5432/servlet");
        properties.setDriverClassName("org.postgresql.Driver");
        properties.setUsername("postgres");
        properties.setPassword("Figa1357");
        DataSource source = new DataSource();
        source.setPoolProperties(properties);
        try {
            connection = source.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Private design of user store.
     */
    private UserStore() {}

    /**
     * Method for get user store instance.
     *
     * @return instance
     */
    public static UserStore getInstance() {
        return instance;
    }

    /**
     * Method for check table for exist.
     */
    private void checkTable() throws SQLException {
        Statement statement = this.connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS USERS (id serial primary key, name text, login text, email text, createDate text)");
        statement.close();
    }

    /**
     * Method for select all users from data base.
     *
     * @return string of all database
     */
    public String get(){
        StringBuilder builder = new StringBuilder("<table>");
        try {
            checkTable();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");
            while (resultSet.next()) {
                builder.append("<tr><td>");
                builder.append(String.format("%s %s %s %s %s",
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        resultSet.getString("createDate")));
                builder.append("</td></tr>");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        builder.append("</table>");
        return builder.toString();
    }

    /**
     * Method for work with data in database.
     *
     * @param sqlScript sql script
     * @param req http request
     * @throws SQLException sql e
     */
    private void workWithDataInDb(String sqlScript, HttpServletRequest req) throws SQLException {
        checkTable();
        PreparedStatement statement = connection.prepareStatement(sqlScript);
        if (sqlScript.startsWith("DELETE")) {
            statement.setInt(1, Integer.valueOf(req.getParameter("id")));
        } else {
            statement.setString(1, req.getParameter("name"));
            statement.setString(2, req.getParameter("login"));
            statement.setString(3, req.getParameter("email"));
            if (sqlScript.startsWith("UPDATE")) {
                statement.setInt(4, Integer.valueOf(req.getParameter("id")));
            } else {
                statement.setString(4, req.getParameter("createDate"));
            }
        }
        statement.executeUpdate();
        statement.close();
    }

    /**
     * Method for post data to database.
     *
     * @param req http request
     */
    public synchronized void post(HttpServletRequest req) {
        try {
            this.workWithDataInDb("INSERT INTO USERS (name, login, email, createDate) VALUES (?, ?, ?, ?)", req);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for put data to database.
     *
     * @param req http request
     */
    public synchronized void put(HttpServletRequest req) {
        try {
            this.workWithDataInDb("UPDATE USERS SET name=?, login=?, email=? WHERE id=?", req);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for delete data from database.
     *
     * @param req http request
     */
    public synchronized void delete(HttpServletRequest req) {
        try {
            this.workWithDataInDb("DELETE FROM USERS WHERE id=?", req);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for close connection when servlet is done.
     */
    public synchronized void closeConnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
