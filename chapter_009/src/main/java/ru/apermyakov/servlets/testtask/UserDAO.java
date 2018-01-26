package ru.apermyakov.servlets.testtask;

import ru.apermyakov.servlets.TransferObject;

import java.sql.*;
import java.util.*;

/**
 * Class for modulate dao for user objects.
 *
 * @author apermyakov
 * @version 1.0
 * @since 19.12.2017
 */
public class UserDAO implements DAOInterface<TransferObject, Integer> {

    /**
     * Field for connection.
     */
    private Connection connection;

    /**
     * Field for psql worker.
     */
    private UserPsqlWorker worker = new UserPsqlWorker();

    /**
     * Design dao.
     *
     * @param connection
     */
    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method for get user's music type.
     *
     * @param user
     * @return
     */
    private List<String> getUserMusicType(TransferObject user) {
        List<String> result = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement("SELECT m.name FROM USERS as u " +
                    "left join users_musictype as um " +
                    "on u.id=um.user_id " +
                    "left join musictype as m " +
                    "on m.id=um.musictype_id " +
                    "where u.id=?"
            )){
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method for create user.
     *
     * @param user
     * @return
     */
    @Override
    public void create(TransferObject user) {
        worker.add(this.connection, user);
    }

    /**
     * Method for chose all users.
     *
     * @return
     */
    @Override
    public List<TransferObject> choseAll() {
        List<TransferObject> result = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT u.id, u.name, r.name as role, a.name as address " +
                    "FROM USERS as u " +
                    "left join role as r " +
                    "on u.role_id=r.id " +
                    "left join address as a " +
                    "on u.address_id=a.id "
            );
            while (resultSet.next()) {
                TransferObject user = new TransferObject();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setRole(resultSet.getString("role"));
                user.setAddress(resultSet.getString("address"));
                user.setMusicType(getUserMusicType(user));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result.sort(Comparator.comparingInt(TransferObject::getId));
        return result;
    }

    /**
     * Method for chose user by id.
     *
     * @param id
     * @return
     */
    @Override
    public TransferObject choseById(Integer id) {
        TransferObject user = new TransferObject();
        try (PreparedStatement statement = this.connection.prepareStatement("SELECT u.id, u.name, r.name as role, a.name as address " +
                "FROM USERS as u " +
                "left join role as r " +
                "on u.role_id=r.id " +
                "left join address as a " +
                "on u.address_id=a.id " +
                "where u.id=?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setRole(resultSet.getString("role"));
                user.setAddress(resultSet.getString("address"));
                user.setMusicType(getUserMusicType(user));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Method for edit user.
     *
     * @param user
     * @return
     */
    @Override
    public void edit(TransferObject user) {
        worker.update(this.connection, user);
    }

    /**
     * Method for delete user.
     *
     * @param id
     * @return
     */
    @Override
    public void delete(Integer id) {
        worker.remove(this.connection, id);
    }
}
