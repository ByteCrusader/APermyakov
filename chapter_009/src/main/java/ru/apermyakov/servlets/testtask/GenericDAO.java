package ru.apermyakov.servlets.testtask;

import ru.apermyakov.servlets.TransferObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class for modulate dao for different transfer objects.
 *
 * @author apermyakov
 * @version 1.0
 * @since 19.12.2017
 */
public class GenericDAO implements DAOInterface<TransferObject, Integer> {

    /**
     * Field for connection.
     */
    private Connection connection;

    /**
     * Field for table.
     */
    private String table;


    /**
     * Method for design generic dao.
     *
     * @param connection
     * @param table target table
     */
    public GenericDAO(Connection connection, String table) {
        this.connection = connection;
        this.table = table;
    }

    /**
     * Method for create object into db.
     *
     * @param object
     */
    @Override
    public void create(TransferObject object) {
        try (PreparedStatement statement = this.connection.prepareStatement(String.format("insert into %s (name) values (?)", table))) {
            statement.setString(1, object.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for chose all objects.
     *
     * @return list of objects
     */
    @Override
    public List<TransferObject> choseAll() {
        List<TransferObject> result = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s", table));
            while (resultSet.next()) {
                TransferObject object = new TransferObject();
                object.setId(resultSet.getInt("id"));
                object.setName(resultSet.getString("name"));
                result.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result.sort(Comparator.comparingInt(TransferObject::getId));
        return result;
    }

    /**
     * Method for chose object by id.
     *
     * @param id id
     * @return object
     */
    @Override
    public TransferObject choseById(Integer id) {
        TransferObject object = new TransferObject();
        try (PreparedStatement statement = this.connection.prepareStatement(String.format("SELECT * FROM %s as t where t.id=?", table))) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                object.setId(resultSet.getInt("id"));
                object.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * Method for edit object.
     *
     * @param object
     */
    @Override
    public void edit(TransferObject object) {
        try (PreparedStatement statement = this.connection.prepareStatement(String.format("update %s set name=? where id=?", table))) {
            statement.setString(1, object.getName());
            statement.setInt(2, object.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for delete object from db.
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        try (PreparedStatement statement = this.connection.prepareStatement(String.format("delete from %s where id=?", table)))
        {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
