package ru.apermyakov.servlets;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.HashMap;

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
     * Field for INSTANCE.
     */
    @GuardedBy("this")
    private static final UserStore INSTANCE = new UserStore();

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
    private UserStore() { }

    /**
     * Method for getUser user store INSTANCE.
     *
     * @return INSTANCE
     */
    public static UserStore getInstance() {
        return INSTANCE;
    }

    /**
     * Method for initial tables of not exists.
     *
     * @param statement statement
     * @throws SQLException sql e
     */
    private void initialTableIfNotExist(Statement statement) throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS ROLE (id serial primary key, name text)");
        statement.executeUpdate("INSERT INTO ROLE (name) select 'administrator' where not EXISTS (select name from role where name = 'administrator')");
        statement.executeUpdate("INSERT INTO ROLE (name) select 'teacher' where not exists (select name from role where name = 'teacher')");
        statement.executeUpdate("INSERT INTO ROLE (name) select 'student' where not exists (select name from role where name = 'student')");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS USERS (id serial primary key, name text, login text, email text, createDate text, password integer, role_id integer REFERENCES role(id))");
    }

    /**
     * Method for check table for exist.
     */
    private void checkTable() throws SQLException {
        Statement statement = this.connection.createStatement();
        initialTableIfNotExist(statement);
        statement.close();
    }

    /**
     * Method for select all users from data base.
     *
     * @return string of all database
     */
    public String getUser() {
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
     * Method for get roles map.
     *
     * @return map of roles
     */
    public HashMap<Integer, String> getRoles() {
        HashMap<Integer, String> result = new HashMap<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ROLE");
            while (resultSet.next()) {
                result.put(resultSet.getInt("id"), resultSet.getString("name"));
            }
            statement.close();
        }        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method for calculate hash of password.
     *
     * @param password password
     * @return hash
     */
    private int hash(String password) {
        int passwordHash = password.hashCode();
        return passwordHash ^ (passwordHash >>> 16);
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
            statement.setInt(4, hash(req.getParameter("password")));
            if (sqlScript.startsWith("UPDATE") && sqlScript.contains("role")) {
                statement.setInt(5, Integer.valueOf(req.getParameter("role")));
                statement.setInt(6, Integer.valueOf(req.getParameter("id")));
            } else if (sqlScript.startsWith("UPDATE") && !sqlScript.contains("role")) {
                statement.setInt(5, Integer.valueOf(req.getParameter("id")));
            } else {
                statement.setString(5, req.getParameter("createDate"));
                if (("teacher").equals(req.getParameter("role"))) {
                    statement.setInt(6, 2);
                } else {
                    statement.setInt(6, 3);
                }
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
            this.workWithDataInDb("INSERT INTO USERS (name, login, email, password, createDate, role_id) VALUES (?, ?, ?, ?, ?, ?)", req);
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
            HttpSession session = req.getSession();
            synchronized (session) {
                if (("admin").equals(session.getAttribute("login"))) {
                    this.workWithDataInDb("UPDATE USERS SET name=?, login=?, email=?, password=?, role_id=? WHERE id=?", req);
                } else {
                    this.workWithDataInDb("UPDATE USERS SET name=?, login=?, email=?, password=? WHERE id=?", req);
                }
            }
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

    /**
     * Method for check auth.
     *
     * @param req req
     * @return auth or not
     */
    public boolean isPastInspection(HttpServletRequest req) {
        boolean result = false;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE login=? and password=?");
            statement.setString(1, req.getParameter("login"));
            statement.setInt(2, hash(req.getParameter("password")));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method for get role by login.
     *
     * @param req req
     * @return role
     */
    public String getRole(HttpServletRequest req) {
        String result = "";
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT name FROM role WHERE id = (SELECT role_id FROM USERS WHERE login=?)");
            HttpSession session = req.getSession();
            synchronized (session) {
                statement.setString(1, (String) session.getAttribute("login"));
            }
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method for get id of login.
     *
     * @param req req
     * @return id
     */
    public int getId(HttpServletRequest req) {
        int result = -1;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT id FROM users WHERE login = ?");
            HttpSession session = req.getSession();
            synchronized (session) {
                statement.setString(1, (String) session.getAttribute("login"));
            }
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
