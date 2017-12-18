package ru.apermyakov.servlets;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
     * Field for map of sessions.
     */
    public HashMap<String, HttpSession> sessions = new HashMap<>();

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
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS COUNTRIES (id serial primary key, name text)");
        statement.executeUpdate("INSERT INTO COUNTRIES (name) select 'Russia' where not EXISTS (select name from COUNTRIES where name = 'Russia')");
        statement.executeUpdate("INSERT INTO COUNTRIES (name) select 'Norway' where not exists (select name from countries where name = 'Norway')");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS cities (id serial primary key, name text, countries_id integer references countries(id))");
        statement.executeUpdate("INSERT INTO cities (name, countries_id) select 'Ekaterinburg', '1' where not EXISTS (select name, countries_id from cities where name = 'Ekaterinburg' and countries_id = '1')");
        statement.executeUpdate("INSERT INTO cities (name, countries_id) select 'Moskow', '1' where not EXISTS (select name, countries_id from cities where name = 'Moskow' and countries_id = '1')");
        statement.executeUpdate("INSERT INTO cities (name, countries_id) select 'St. Petersburg', '1' where not EXISTS (select name, countries_id from cities where name = 'St. Petersburg' and countries_id = '1')");
        statement.executeUpdate("INSERT INTO cities (name, countries_id) select 'Oslo', '2' where not EXISTS (select name, countries_id from cities where name = 'Oslo' and countries_id = '2')");
        statement.executeUpdate("INSERT INTO cities (name, countries_id) select 'Bergen', '2' where not EXISTS (select name, countries_id from cities where name = 'Bergen' and countries_id = '2')");
        statement.executeUpdate("INSERT INTO cities (name, countries_id) select 'Trondheim', '2' where not EXISTS (select name, countries_id from cities where name = 'Trondheim' and countries_id = '2')");
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
     * @return
     */
    public TreeMap<Integer, HashMap<String, String>> getUsersMap() {
       TreeMap<Integer, HashMap<String,String>> result = new TreeMap<>();
        try {
            checkTable();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT u.id, u.name, u.login, u.email, u.createDate, r.name as role, c.name as country, s.name as city " +
                                                                "FROM USERS as u " +
                                                                "left join role as r " +
                                                                "on u.role_id=r.id " +
                                                                "left join countries as c " +
                                                                "on u.countries_id=c.id " +
                                                                "left join cities as s " +
                                                                "on u.cities_id=s.id");
            while (resultSet.next()) {
                result.put(resultSet.getInt("id"), new HashMap<>());
                result.get(resultSet.getInt("id")).put("name", resultSet.getString("name"));
                result.get(resultSet.getInt("id")).put("login", resultSet.getString("login"));
                result.get(resultSet.getInt("id")).put("email", resultSet.getString("email"));
                result.get(resultSet.getInt("id")).put("createDate", resultSet.getString("createDate"));
                result.get(resultSet.getInt("id")).put("role", resultSet.getString("role"));
                result.get(resultSet.getInt("id")).put("country", resultSet.getString("country"));
                result.get(resultSet.getInt("id")).put("city", resultSet.getString("city"));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method for get table map.
     *
     * @return map of table
     */
    public HashMap<Integer, String> getMap(String table) {
        HashMap<Integer, String> result = new HashMap<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s", table));
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
     * @param country
     * @return
     */
    public HashMap<Integer, String> getCitiesByCountry(String country) {
        HashMap<Integer, String> result = new HashMap<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT ci.id, ci.name FROM cities as ci left join countries as co on ci.countries_id=co.id where co.name = '%s'", country));
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
     * @param table
     * @param param
     * @return
     */
    private int paramToId(String table, String param) {
        int result = -1;
        for (Map.Entry<Integer, String> entry : this.getMap(table).entrySet()) {
            if (param.equals(entry.getValue())) {
                result = entry.getKey();
            }
        }
        return result;
    }

    /**
     * @return
     */
    private String createDate() {
        SimpleDateFormat format =  new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return format.format(Calendar.getInstance().getTime());
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
            statement.setInt(5, paramToId("countries", req.getParameter("country")));
            statement.setInt(6, paramToId("cities", req.getParameter("city")));
            if (sqlScript.startsWith("UPDATE") && sqlScript.contains("role")) {
                statement.setInt(7, paramToId("role", req.getParameter("role")));
                statement.setInt(8, Integer.valueOf(req.getParameter("id")));
            } else if (sqlScript.startsWith("UPDATE") && !sqlScript.contains("role")) {
                statement.setInt(7, Integer.valueOf(req.getParameter("id")));
            } else {
                statement.setString(7, createDate());
                statement.setInt(8, paramToId("role", req.getParameter("role")));
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
            this.workWithDataInDb("INSERT INTO USERS (name, login, email, password, countries_id, cities_id, createDate, role_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", req);
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
            if (("administrator").equals(session.getAttribute("role"))) {
                this.workWithDataInDb("UPDATE USERS SET name=?, login=?, email=?, password=?, countries_id=?, cities_id=?, role_id=? WHERE id=?", req);
            } else {
                this.workWithDataInDb("UPDATE USERS SET name=?, login=?, email=?, password=?, countries_id=?, cities_id=? WHERE id=?", req);
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
     * @param login
     * @param password
     * @return
     */
    public boolean isPastInspection(String login, String password) {
        boolean result = false;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE login=? and password=?");
            statement.setString(1, login);
            statement.setInt(2, hash(password));
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
     * @param login
     * @return
     */
    public String getRole(String login) {
        String result = "";
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT name FROM role WHERE id = (SELECT role_id FROM USERS WHERE login=?)");
                statement.setString(1, login);
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
     * @param login
     * @return
     */
    public String getId(String login) {
        String result = "";
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT id FROM users WHERE login = ?");
                statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = String.valueOf(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
