package ru.apermyakov.JDBC;

import java.io.*;
import java.sql.*;

/**
 * JavaBean class for work with db and xml.
 *
 * @author apermyakov
 * @version 1.0
 * @since 27.11.2017
 */
public class DataBaseBean {

    /**
     * Field for url of sqlite db.
     */
    private String urlOfDb;

    /**
     * Field for number of records.
     */
    private int numberOfRecords;

    /**
     * Method for get url of database.
     *
     * @return url
     */
    public String getUrlOfDb() {
        return urlOfDb;
    }

    /**
     * Method for set url of database.
     *
     * @param urlOfDb url
     */
    public void setUrlOfDb(String urlOfDb) {
        this.urlOfDb = urlOfDb;
    }

    /**
     * Method for get numbers of records.
     *
     * @return number
     */
    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    /**
     * Method for set numbers of records.
     *
     * @param numberOfRecords numbers
     */
    public void setNumberOfRecords(int numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }

    /**
     * Method for main action of bean.
     */
    public void action() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        try {
            if (!(new File(this.getUrlOfDb())).exists()) {
                new File(this.getUrlOfDb()).createNewFile();
            }
            conn = DriverManager.getConnection("jdbc:sqlite:" + this.getUrlOfDb());

            Initiator initiator = new Initiator(conn, getNumberOfRecords());
            Calculator calculator = new Calculator();

            initiator.initialTable();
            initiator.initialFirstXML();
            initiator.initialSecondXML();
            calculator.calculateAndShowResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Override equals.
     *
     * @param o object
     * @return ><=
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataBaseBean that = (DataBaseBean) o;

        if (numberOfRecords != that.numberOfRecords) return false;
        return urlOfDb != null ? urlOfDb.equals(that.urlOfDb) : that.urlOfDb == null;
    }

    /**
     * Override hash code.
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        int result = urlOfDb != null ? urlOfDb.hashCode() : 0;
        result = 31 * result + numberOfRecords;
        return result;
    }

    /**
     * Override to string.
     *
     * @return to string
     */
    @Override
    public String toString() {
        return "DataBaseBean{" +
                "url='" + urlOfDb + '\'' +
                ", n=" + numberOfRecords +
                '}';
    }
}
