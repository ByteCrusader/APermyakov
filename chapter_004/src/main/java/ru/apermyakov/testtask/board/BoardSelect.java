package ru.apermyakov.testtask.board;

import ru.apermyakov.testtask.cell.Sell;

/**
 * Interface for select board sell.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public interface BoardSelect {

    /**
     * Method for select sell.
     *
     * @param height height coordinate.
     * @param width width coordinate.
     * @return found sell.
     */
    Sell selectSell(int height, int width);
}
