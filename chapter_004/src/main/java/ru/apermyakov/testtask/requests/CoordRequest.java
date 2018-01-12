package ru.apermyakov.testtask.requests;

import java.util.Map;

/**
 * Interface for coordinate request.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public interface CoordRequest {

    /**
     * Method for request coordinates.
     *
     * @return map of coordinates.
     */
    Map<String, Integer> requestCoordinates();
}
