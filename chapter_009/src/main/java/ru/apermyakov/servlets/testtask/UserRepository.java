package ru.apermyakov.servlets.testtask;

import ru.apermyakov.servlets.TransferObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for modulate user repository.
 *
 * @author apermyakov
 * @version 1.0
 * @since 20.12.2017
 */
public class UserRepository implements RepositoryInterface<TransferObject> {

    /**
     * Field for psql connector helper.
     */
    private PsqlConnector connector = new PsqlConnector();

    /**
     * Field for psql worker.
     */
    private UserPsqlWorker worker = new UserPsqlWorker();

    /**
     * Method for add object.
     *
     * @param object
     */
    @Override
    public void add(TransferObject object) {
        try (Connection connection = this.connector.getConnection()) {
            worker.add(connection, object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for update object.
     *
     * @param object
     */
    @Override
    public void update(TransferObject object) {
        try (Connection connection = this.connector.getConnection()) {
            worker.add(connection, object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for remove object.
     *
     * @param object
     */
    @Override
    public void remove(TransferObject object) {
        try (Connection connection = this.connector.getConnection()) {
            worker.add(connection, object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for get some object by specification.
     *
     * @param specification
     * @return
     */
    @Override
    public List<TransferObject> query(Specification specification) {
        List<TransferObject> result = new ArrayList<>();
        try (Connection connection = this.connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(specification.toSqlString())
            ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TransferObject object = new TransferObject();
                object.setId(resultSet.getInt("id"));
                object.setName(resultSet.getString("name"));
                object.setRole(resultSet.getString("role"));
                object.setAddress(resultSet.getString("address"));
                object.setMusicType(new ArrayList<>(Arrays.asList(resultSet.getString("music_types").replaceAll("[(\")]", "").split(","))));
                result.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
