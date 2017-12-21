package ru.apermyakov.servlets.testtask;

import java.util.List;

/**
 * @param <T>
 * @param <K>
 */
public interface DAOInterface<T, K> {

    /**
     * @param item
     * @return
     */
    void create(T item);

    /**
     * @return
     */
    List<T> choseAll();

    /**
     * @param id
     * @return
     */
    T choseById(K id);

    /**
     * @param item
     * @return
     */
    void edit(T item);

    /**
     * @param id
     * @return
     */
    void delete(K id);
}
