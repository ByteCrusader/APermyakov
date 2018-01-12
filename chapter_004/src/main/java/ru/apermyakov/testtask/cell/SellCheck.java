package ru.apermyakov.testtask.cell;

/**
 * Interface for check sell.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public interface SellCheck {

    /**
     * Method for check is this sell coincidence for coordinates.
     *
     * @param height height coordinate.
     * @param width width coordinate.
     * @return is coincidence.
     */
    boolean isCoincidence(int height, int width);
}
