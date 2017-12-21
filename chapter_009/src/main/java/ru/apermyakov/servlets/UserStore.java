package ru.apermyakov.servlets;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private void checkTable() {
        try (Statement statement = this.connection.createStatement()) {
            initialTableIfNotExist(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for get all users from DB.
     *
     * @return list of users
     */
    public List<TransferObject> getUsers() {
        List<TransferObject> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            checkTable();
            ResultSet resultSet = statement.executeQuery("SELECT u.id, u.name, u.login, u.email, u.createDate, r.name as role, c.name as country, s.name as city " +
                                                                "FROM USERS as u " +
                                                                "left join role as r " +
                                                                "on u.role_id=r.id " +
                                                                "left join countries as c " +
                                                                "on u.countries_id=c.id " +
                                                                "left join cities as s " +
                                                                "on u.cities_id=s.id");
            while (resultSet.next()) {
                TransferObject user = new TransferObject();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLogin(resultSet.getString("login"));
                user.setEmail(resultSet.getString("email"));
                user.setCreateDate(resultSet.getString("createDate"));
                user.setRole(resultSet.getString("role"));
                user.setCountry(resultSet.getString("country"));
                user.setCity(resultSet.getString("city"));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result.sort(new Comparator<TransferObject>() {
            @Override
            public int compare(TransferObject o1, TransferObject o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });
        return result;
    }

    /**
     * Method for get table map.
     *
     * @return map of table
     */
    public HashMap<Integer, String> getMap(String table) {
        HashMap<Integer, String> result = new HashMap<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s", table));
            while (resultSet.next()) {
                result.put(resultSet.getInt("id"), resultSet.getString("name"));
            }
        }        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method for get city by country
     *
     * @param country country
     * @return city
     */
    public HashMap<Integer, String> getCitiesByCountry(String country) {
        HashMap<Integer, String> result = new HashMap<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format("SELECT ci.id, ci.name FROM cities as ci left join countries as co on ci.countries_id=co.id where co.name = '%s'", country));
            while (resultSet.next()) {
                result.put(resultSet.getInt("id"), resultSet.getString("name"));
            }
        } catch (SQLException e) {
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
     * Method for convert param to id
     *
     * @param table needed table
     * @param param param
     * @return id
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
     * Method for create date to user
     *
     * @return current date
     */
    private String createDate() {
        SimpleDateFormat format =  new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return format.format(Calendar.getInstance().getTime());
    }

    /**
     * Method for work with data in database.
     *
     * @param sqlScript sql script
     * @param user user
     */
    private void workWithDataInDb(String sqlScript, TransferObject user){
        checkTable();
        try (PreparedStatement statement = connection.prepareStatement(sqlScript)) {
            if (sqlScript.startsWith("DELETE")) {
                statement.setInt(1, user.getId());
            } else {
                statement.setString(1, user.getName());
                statement.setString(2, user.getLogin());
                statement.setString(3, user.getEmail());
                statement.setInt(4, hash(user.getPassword()));
                statement.setInt(5, paramToId("countries", user.getCountry()));
                statement.setInt(6, paramToId("cities", user.getCity()));
                if (sqlScript.startsWith("UPDATE") && sqlScript.contains("role")) {
                    statement.setInt(7, paramToId("role", user.getRole()));
                    statement.setInt(8, user.getId());
                } else if (sqlScript.startsWith("UPDATE") && !sqlScript.contains("role")) {
                    statement.setInt(7, user.getId());
                } else {
                    statement.setString(7, createDate());
                    statement.setInt(8, paramToId("role", user.getRole()));
                }
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for post data to database.
     *
     * @param user user
     */
    public void post(TransferObject user) {
        this.workWithDataInDb("INSERT INTO USERS (name, login, email, password, countries_id, cities_id, createDate, role_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", user);
    }

    /**
     * Method for put data to database.
     *
     * @param currentUser current user
     * @param targetUser target user
     */
    public void put(TransferObject currentUser, TransferObject targetUser) {
        final String admin = "administrator";
        if (admin.equals(currentUser.getRole())) {
            this.workWithDataInDb("UPDATE USERS SET name=?, login=?, email=?, password=?, countries_id=?, cities_id=?, role_id=? WHERE id=?", targetUser);
        } else {
            this.workWithDataInDb("UPDATE USERS SET name=?, login=?, email=?, password=?, countries_id=?, cities_id=? WHERE id=?", targetUser);
        }
    }

    /**
     * Method for delete data from database.
     *
     * @param user user
     */
    public void delete(TransferObject user) {
        this.workWithDataInDb("DELETE FROM USERS WHERE id=?", user);
    }

    /**
     * Method for check user.
     *
     * @param user user
     * @return exist or not
     */
    public boolean isPastInspection(TransferObject user) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE login=? and password=?")) {
            statement.setString(1, user.getLogin());
            statement.setInt(2, hash(user.getPassword()));
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
     * Method for get user's role.
     *
     * @param user user
     * @return role
     */
    public String getRole(TransferObject user) {
        String result = "";
        try (PreparedStatement statement = connection.prepareStatement("SELECT name FROM role WHERE id = (SELECT role_id FROM USERS WHERE login=?)")) {
            statement.setString(1, user.getLogin());
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
     * Method for get user's id.
     *
     * @param user user
     * @return id
     */
    public String getId(TransferObject user) {
        String result = "";
        try (PreparedStatement statement = connection.prepareStatement("SELECT id FROM users WHERE login = ?")) {
            statement.setString(1, user.getLogin());
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
