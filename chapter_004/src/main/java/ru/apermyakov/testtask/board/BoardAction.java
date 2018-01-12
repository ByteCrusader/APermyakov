package ru.apermyakov.testtask.board;

/**
 * Interface for modulate board value.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public interface BoardAction {

    /**
     * Method for set value to board sell by coordinates.
     *
     * @param height height coordinate.
     * @param width width coordinate.
     * @param isCross value.
     */
    void setValue(int height, int width, boolean isCross);
}
