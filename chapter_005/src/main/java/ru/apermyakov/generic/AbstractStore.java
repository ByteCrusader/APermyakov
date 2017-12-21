package ru.apermyakov.generic;

/**
 * Class for store logic.
 *
 * @author apermyakov
 * @version 1.0
 * @since 01.11.2017
 * @param <T> type of data
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {

    /**
     * Field for initial container.
     */
    private SimpleArray<T> container = new SimpleArray<>();

    /**
     * Field for calculate length of container.
     */
    private int length = 0;

    /**
     * Method for override add data to container.
     *
     * @param model data
     * @return added data
     */
    @Override
    public T add(T model) {
        container.add(model);
        length++;
        return returnStatement(model.getId());
    }

    /**
     * Method for override update data in container.
     *
     * @param model data
     * @return updated data
     */
    @Override
    public T update(T model) {
        T old = returnStatement(model.getId());
        container.update(old, model);
        return returnStatement(model.getId());
    }

    /**
     * Method for override delete data out of container.
     *
     * @param id data id
     * @return deleted or exception
     */
    @Override
    public boolean delete(String id) {
        container.delete(returnStatement(id));
        length--;
        return true;
    }

    /**
     * Method for check if container has this data.
     *
     * @param id data id
     * @return found data
     * @throws IllegalArgumentException container has'n such data
     */
    private T returnStatement(String id) throws IllegalArgumentException {
        for (int count = 0; count < length; count++) {
            if (container.get(count).getId().equals(id)) {
                return container.get(count);
            }
        }
        throw new IllegalArgumentException("Container has't such data.");
    }
}
