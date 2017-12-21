package ru.apermyakov.servlets.testtask;

/**
 * Interface for build specification model.
 *
 * @author apermyakov
 * @version 1.0
 * @since 21.12.2017
 */
public interface Specification {

    /**
     * Method for build sql script string.
     *
     * @return
     */
    String toSqlString();
}
