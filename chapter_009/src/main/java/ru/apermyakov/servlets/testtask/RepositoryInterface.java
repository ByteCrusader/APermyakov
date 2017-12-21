package ru.apermyakov.servlets.testtask;

import java.util.List;

/**
 * Interface for build repository.
 *
 * @author apermyakov
 * @version 1.0
 * @since 20.12.2017
 * @param <TransferObject>
 */
public interface RepositoryInterface<TransferObject> {

    /**
     * Method for add object.
     *
     * @param object
     */
    void add(TransferObject object);

    /**
     * Method for update object.
     *
     * @param object
     */
    void update(TransferObject object);

    /**
     * Method for remove object.
     *
     * @param object
     */
    void remove(TransferObject object);

    /**
     * Method for get come object by specification.
     *
     * @param specification
     * @return
     */
    List<TransferObject> query(Specification specification);
}
