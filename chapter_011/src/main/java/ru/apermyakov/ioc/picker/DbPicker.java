package ru.apermyakov.ioc.picker;

import java.util.Map;

/**
 * Interface for data base picker.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
public interface DbPicker extends Picker {

    /**
     * Method for make connection settings.
     *
     * @param connSettings connection settings.
     */
    void makeConnSettings(Map<String, String> connSettings);
}
