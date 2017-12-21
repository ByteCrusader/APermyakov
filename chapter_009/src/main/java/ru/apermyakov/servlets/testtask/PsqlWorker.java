package ru.apermyakov.servlets.testtask;

import ru.apermyakov.servlets.TransferObject;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for work with db.
 *
 * @author apermyakov
 * @version 1.0
 * @since 20.12.2017
 */
public class PsqlWorker {

    /**
     * Method for get table map.
     *
     * @return map of table
     */
    private HashMap<Integer, String> getMap(Connection connection, String table) {
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
     * Method for convert param to id
     *
     * @param table needed table
     * @param param param
     * @return id
     */
    protected int paramToId(Connection connection, String table, String param) {
        int result = -1;
        for (Map.Entry<Integer, String> entry : this.getMap(connection, table).entrySet()) {
            if (param.equals(entry.getValue())) {
                result = entry.getKey();
            }
        }
        return result;
    }

    /**
     * Method for add object to db.
     *
     * @param connection
     */
    protected void add(Connection connection, String table, String column, String value) {
        try (PreparedStatement statement = connection.prepareStatement("insert into ? (?) values (?)")) {
            statement.setString(1, table);
            statement.setString(2, column);
            statement.setString(3, value);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for update object into db.
     *
     * @return
     */
    protected void update(Connection connection, String table, String column, String value, TransferObject object) {
        try (PreparedStatement statement = connection.prepareStatement("update ? set ?=? where id=?")) {
            statement.setString(1, table);
            statement.setString(2, column);
            statement.setString(3, value);
            statement.setInt(4, object.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method for remove object out of db.
     *
     * @param value
     * @return
     */
    protected void remove(Connection connection, String table, String column, String value) {
        try (PreparedStatement statement = connection.prepareStatement("delete from ? where ?=?")) {
            statement.setString(1, table);
            statement.setString(2, column);
            statement.setString(3, value);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
