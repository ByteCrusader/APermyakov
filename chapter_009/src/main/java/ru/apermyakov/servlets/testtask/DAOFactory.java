package ru.apermyakov.servlets.testtask;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class for modulate dao factory.
 *
 * @author apermyakov
 * @version 1.0
 * @since 19.12.2017
 */
public class DAOFactory {

    /**
     * Filed for psql connector helper.
     */
    private PsqlConnector connector = new PsqlConnector();

    /**
     * Method for get connection to DB.
     *
     * @return connection
     */
    public Connection getConnection() throws SQLException {
        return this.connector.getConnection();
    }

    /**
     * Method for get user dao.
     *
     * @param connection
     * @return user dao
     */
    public UserDAO getUserDAO(Connection connection) {
        return new UserDAO(connection);
    }

    /**
     * Method for get generic dao.
     *
     * @param connection
     * @param table target table
     * @return target table dao
     */
    public GenericDAO getGenericDAO(Connection connection, String table) {
        return new GenericDAO(connection, table);
    }
}
