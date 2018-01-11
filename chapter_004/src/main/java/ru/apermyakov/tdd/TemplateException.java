package ru.apermyakov.tdd;

/**
 * Method for generate generator exception.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 11.01.2018.
 */
public class TemplateException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param message message.
     */
    public TemplateException(String message) {
        super(message);
    }
}
