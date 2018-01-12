package ru.apermyakov.testtask.backend;

import ru.apermyakov.testtask.cell.Sell;

/**
 * Interface for backend win.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public interface BackendCheckWin {

    /**
     * Method for check win by sell.
     *
     * @param sell sell.
     * @return win or not.
     */
    boolean checkWin(Sell sell);
}
