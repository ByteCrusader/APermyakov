package ru.apermyakov.testtask.board;

import ru.apermyakov.testtask.cell.Sell;

import java.util.List;

/**
 * Interface for get all sells.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public interface BoardSells {

    /**
     * Method for get sells.
     *
     * @return list of sells.
     */
    List<Sell> getSells();
}
