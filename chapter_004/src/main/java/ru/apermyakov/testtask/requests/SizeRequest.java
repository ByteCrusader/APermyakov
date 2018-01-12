package ru.apermyakov.testtask.requests;

import java.util.Map;

/**
 * Interface for size request.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public interface SizeRequest {

    /**
     * Method for request size.
     *
     * @return map of sizes.
     */
    Map<String, Integer> requestSize();
}
