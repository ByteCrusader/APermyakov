package ru.apermyakov.ioc.storage;

import java.util.Map;

/**
 * Interface for annotation storage.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
public interface DbStorage extends Storage {
    /**
     * Method for make connection settings.
     *
     * @param connSettings connection settings.
     */
    void makeConnSettings(Map<String, String> connSettings);
}
