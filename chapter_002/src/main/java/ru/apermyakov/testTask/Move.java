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
     * Method for realize move to up and right.
     *
	 * @param base base cell of figure
	 * @param xIndex increment of x
	 * @param yIndex increment of y
     * @return new figure cell
     * @since 23.10.2017
     */
    public Cell upRight(Cell base, int xIndex, int yIndex) {
        return new Cell(base.getX() + xIndex, base.getY() + yIndex);
    }

    /**
     * Method for realize move to down and right.
     *
	 * @param base base cell of figure
	 * @param xIndex increment of x
	 * @param yIndex decrement of y
     * @return new figure cell
     * @since 23.10.2017
     */
    public Cell downRight(Cell base, int xIndex, int yIndex) {
        return new Cell(base.getX() + xIndex, base.getY() - yIndex);
    }

    /**
     * Method for realize move to down and left.
     *
	 * @param base base cell of figure
	 * @param xIndex decrement of x
	 * @param yIndex decrement of y
     * @return new figure cell
     * @since 23.10.2017
     */
    public Cell downLeft(Cell base, int xIndex, int yIndex) {
        return new Cell(base.getX() - xIndex, base.getY() - yIndex);
    }

    /**
     * Method for realize move to up and left.
     *
	 * @param base base cell of figure
	 * @param xIndex decrement of x
	 * @param yIndex increment of y
     * @return new figure cell
     * @since 23.10.2017
     */
    public Cell upLeft(Cell base, int xIndex, int yIndex) {
        return new Cell(base.getX() - xIndex, base.getY() + yIndex);
    }
}
