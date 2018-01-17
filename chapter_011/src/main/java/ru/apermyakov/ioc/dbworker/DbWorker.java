package ru.apermyakov.ioc.dbworker;

import java.util.Map;

/**
 * Interface for data base work.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
public interface DbWorker {

    /**
     * Method for build connection settings.
     *
     * @return map of settings.
     */
    Map<String, String> buildConnSettings();
}
