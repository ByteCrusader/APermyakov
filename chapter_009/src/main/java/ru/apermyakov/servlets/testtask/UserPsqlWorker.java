package ru.apermyakov.servlets.testtask;

import ru.apermyakov.servlets.TransferObject;

import java.sql.*;
import java.util.List;

/**
 * Class for modulate user psql action.
 *
 * @author apermyakov
 * @version 1.0
 * @since 20.12.2017
 */
public class UserPsqlWorker extends PsqlWorker {

    /**
     * Method for insert user's music type.
     *
     * @param user
     * @return
     */
    private void insertUserMusicType(Connection connection, TransferObject user) {
        List<String> musicTypes = user.getMusicType();
        for (String musicType : musicTypes) {
            try (PreparedStatement statement = connection.prepareStatement("insert into users_musictype (user_id, musictype_id) " +
                    "select ?, ? where not EXISTS (" +
                    "select *.user_id, *.musictype_id " +
                    "from users_musictype " +
                    "where user_id = ? and musictype_id = ?" +
                    ")")) {
                for (int count = 1; count < 5;) {
                    statement.setInt(count++, user.getId());
                    statement.setInt(count++, paramToId(connection, "musictype", musicType));
                }
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method for delete user's music type.
     *
     * @return
     */
    private void deleteUserMusicType(Connection connection, Integer id) {
        super.remove(connection, "musictype", "user_id", String.valueOf(id));
    }

    /**
     * Method for update user's music type.
     *
     * @param user
     * @return
     */
    private void updateUserMusicType(Connection connection, TransferObject user) {
        this.deleteUserMusicType(connection, user.getId());
        this.insertUserMusicType(connection, user);
    }

    /**
     * Method for add user.
     *
     * @param connection
     * @param object
     */
    protected void add(Connection connection, TransferObject object) {
        try (PreparedStatement statement = connection.prepareStatement("insert into users (name, address_id, role_id) values (?, ?, ?)")) {
            statement.setString(1, object.getName());
            statement.setInt(2, paramToId(connection,"address", object.getAddress()));
            statement.setInt(3, paramToId(connection,"role", object.getRole()));
            statement.executeUpdate();
            this.insertUserMusicType(connection, object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for update user.
     *
     * @return
     */
    protected void update(Connection connection, TransferObject object) {
        try (PreparedStatement statement = connection.prepareStatement("update users set name=?, address_id=?, role_id=? where id=?")) {
            statement.setString(1, object.getName());
            statement.setInt(2, paramToId(connection, "address", object.getAddress()));
            statement.setInt(3, paramToId(connection, "role", object.getRole()));
            statement.setInt(4, object.getId());
            statement.executeUpdate();
            this.updateUserMusicType(connection, object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for remove user.
     *
     * @param id
     * @return
     */
    protected void remove(Connection connection, Integer id) {
        this.deleteUserMusicType(connection, id);
        super.remove(connection, "users", "id", String.valueOf(id));
    }
}
