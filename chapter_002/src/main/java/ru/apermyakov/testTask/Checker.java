package ru.apermyakov.testtask;

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
        boolean result = false;
        for (int index = 0; index < existFigures.length; index++) {
            if (source.getX() == existFigures[index].position.getX() && source.getY() == existFigures[index].position.getY()) { //Check cell existence
                this.figure = existFigures[index];
                this.index = index;
                result = true;
                break;
            }
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
        boolean result = false;
        for (Cell possibleMove : figure.possibleMoves()) { //array of possible moves
            if (dist.getX() == possibleMove.getX() && dist.getY() == possibleMove.getY()) { //Check possible move
                result = true;
                break;
            }
        }
        return result;
    }

	/**
     * Method for check way of figure.
     *
     * @return free or not
     * @since 23.10.2017
     */
    public boolean checkWay() {
        boolean result = true;
        for (Cell wayPoint : figure.way(dist)) { //array of way points
            for (Figure busy : existFigures) { // array of existing figures
                if (wayPoint.getX() == busy.position.getX() && wayPoint.getY() == busy.position.getY()) { //Check way points
                    result = false; //let count
                    break;
                }
            }
        }
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
