package ru.apermyakov.servlets.testtask;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

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
    public Connection getConnection() throws SQLException, IOException {
        PoolProperties properties = new PoolProperties();
        Properties appProperties = new Properties();
        appProperties.load(this.getClass().getClassLoader().getResourceAsStream("app.properties"));
        properties.setUrl(appProperties.getProperty("url"));
        properties.setDriverClassName(appProperties.getProperty("driver"));
        properties.setUsername(appProperties.getProperty("username"));
        properties.setPassword(appProperties.getProperty("password"));
        DataSource source = new DataSource();
        source.setPoolProperties(properties);
        return source.getConnection();
    }
}
