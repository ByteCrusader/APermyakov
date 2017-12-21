package ru.apermyakov.generic;

/**
 * Interface for store action.
 *
 * @author apermyakov
 * @version 1.0
 * @since 01.11.2017
 * @param <T> type of store data
 */
public interface Store<T extends Base> {
    /**
     * Method for add data to container.
     *
     * @param model data
     * @return added data
     */
    T add(T model);

    /**
     * Method for update data to container.
     *
     * @param model data
     * @return updated data
     */
    T update(T model);

    /**
     * Method for delete data out of container.
     *
     * @param id data id
     * @return delete or not
     */
    boolean delete(String id);
}
