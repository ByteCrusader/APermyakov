package ru.apermyakov.testtask;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
* Class for check move of figure.
*
* @author apermyakov
* @version 1.0
* @since 23.10.2017
*/
public class Checker {

	/**
	* Initial source cell of figure.
	*/
    private Cell source;

	/**
	* Initial destination cell of figure.
	*/
    private Cell dist;

	/**
	* Initial array of exist figures.
	*/
    private Figure[] existFigures;

	/**
	* Initial figure.
	*/
    private Figure figure;

	/**
	* Initial index of figure in array of all figures.
	*/
    private int index;

	/**
	* Design checker.
	*
	* @param existFigures array of exist figures
	* @param source source cell of figure
	* @param dist destination cell of figure
	*/
    public Checker(Figure[] existFigures, Cell source, Cell dist) {
        this.existFigures = existFigures;
        this.source = source;
        this.dist = dist;
    }

	/**
     * Method for check existence of figure.
     *
     * @return exist or not
     * @since 23.10.2017
     */
    public boolean checkFigure() {
        boolean result = Arrays.stream(this.existFigures).anyMatch(i -> i.position.getX() == this.source.getX() && i.position.getY() == this.source.getY());
        if (result) { //Check cell existence
            this.figure = Arrays.stream(this.existFigures).filter(i -> i.position.getX() == this.source.getX() && i.position.getY() == this.source.getY()).findFirst().get();
            this.index = Arrays.stream(this.existFigures).collect(Collectors.toList()).indexOf(this.figure);
        }
        return result;
    }

	/**
     * Method for check possibility of figure move.
     *
     * @return possible or not
     * @since 23.10.2017
     */
    public boolean checkMove() {
        return Arrays.stream(this.figure.possibleMoves()).anyMatch(i -> this.dist.getX() == i.getX() && this.dist.getY() == i.getY());
    }

	/**
     * Method for check way of figure.
     *
     * @return free or not
     * @since 23.10.2017
     */
    public boolean checkWay() {
        boolean result = Arrays.stream(this.figure.way(this.dist)).noneMatch(i -> Arrays.stream(this.existFigures).anyMatch(n -> i.getX() == n.position.getX() && i.getY() == n.position.getY()));
        return (result || figure.getClass().toString().equals("class ru.apermyakov.testtask.Horse"));
    }

	/**
     * Method for get index of figure.
     *
     * @return figure's index
     * @since 23.10.2017
     */
    public int getIndex() {
        return this.index;
    }

	/**
     * Method for get figure.
     *
     * @return figure
     * @since 23.10.2017
     */
    public Figure getFigure() {
        return this.figure;
    }
}
