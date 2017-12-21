package ru.apermyakov.servlets.testtask;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class for work with psql db connect.
 *
 * @author apermyakov
 * @version 1.0
 * @since 20.12.2017
 */
public class PsqlConnector {

    /**
     * Method for get connection by psql db.
     *
     * @return connection
     */
    public Connection getConnection() throws SQLException {
        PoolProperties properties = new PoolProperties();
        properties.setUrl("jdbc:postgresql://localhost:5432/servlettest");
        properties.setDriverClassName("org.postgresql.Driver");
        properties.setUsername("postgres");
        properties.setPassword("Figa1357");
        DataSource source = new DataSource();
        source.setPoolProperties(properties);
        return source.getConnection();
    }
}
