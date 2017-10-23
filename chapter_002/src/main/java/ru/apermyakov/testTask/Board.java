package ru.apermyakov.testtask;

import java.util.Arrays;

/**
* Сlass for chess board.
*
* @author apermyakov
* @version 1.0
* @since 20.10.2017
*/
public class Board {

	/**
	* Initial figures on a board.
	*/
	private Figure[] figures = new Figure[28];

	/**
	* Initial figures array counter.
	*/
	private int position = 0;

	/**
	* Method for initial figure and add for figures array.
	*
	* @author apermyakov
	* @param figure new figure
	* @since 20.10.2017
	*/
	public void initial(Figure figure) {
		this.figures[position++] = figure;
	}

	/**
	 * Method for check and move figure.
	 *
	 * @author apermyakov
	 * @param source dispatch
	 * @param dist destination
	 * @throws ImpossibleMoveException if impossible to move
	 * @throws FigureNotFoundException if figure not found
	 * @throws OccupiedWayException if way is occupied
	 * @return opportunity of move
	 * @since 20.10.2017
	 */
	public boolean move(Cell source, Cell dist) throws ImpossibleMoveException, FigureNotFoundException, OccupiedWayException {
		boolean opportunity = false;
		Checker checker = new Checker(Arrays.copyOf(figures, position), source, dist);
		if (checker.checkFigure()) {
			if (checker.checkMove()) {
				if (checker.checkWay()) {
					opportunity = true;
					this.figures[checker.getIndex()] = checker.getFigure().clone(dist); //re-record the position of the figure in our array of figures
				} else {
					throw new OccupiedWayException("Occupied way"); //Occupied way
				}
			} else {
				throw new ImpossibleMoveException("Impossible to move"); //Impossible move
			}
		} else {
			throw new FigureNotFoundException("Figure not found"); //Figure not found
		}
		return opportunity;
	}

	/**
	* Method for initial figure and add for figures array.
	*
	* @author apermyakov
	* @return figure new figure
	* @since 20.10.2017
	*/
	public Figure[] getFigures() {
		return this.figures;
	}
}