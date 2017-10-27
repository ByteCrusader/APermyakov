package ru.apermyakov.testtask;

/**
* Class for realize move of figure.
*
* @author apermyakov
* @version 1.0
* @since 23.10.2017
*/
public class Move {

    /**
     * Method for move figure somewhere.
     *
     * @param base base cell of figure
     * @param xIndex increment of x
     * @param yIndex increment of y
     * @return new figure cell
     * @since 27.10.2017
     */
    public Cell moveSomewhere(Cell base, int xIndex, int yIndex) {
        return new Cell(base.getX() + xIndex, base.getY() + yIndex);
    }
}
