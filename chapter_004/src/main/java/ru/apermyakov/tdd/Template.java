package ru.apermyakov.tdd;

import java.util.Map;

/**
 * Interface for modulate template.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 11.01.2018.
 */
public interface Template {

    /**
     * Method for generate string using template and meanings.
     *
     * @param template template.
     * @param meanings meanings.
     * @return result string.
     */
    String generate(String template, Map<String, String> meanings);
}
