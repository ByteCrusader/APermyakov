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
		Figure[] existFigures = Arrays.copyOf(figures, position);
		int indexOfFigures = 0;
		int indexOfPossible = 0;
		for (Figure figure : existFigures) { //array of figure
			if (source.getX() == figure.position.getX() && source.getY() == figure.position.getY()) { //Check cell existence
				for (Cell possibleMove : figure.possibleMoves()) { //array of possible moves
					if (dist.getX() == possibleMove.getX() && dist.getY() == possibleMove.getY()) { //Check possible move
						int let = 0; //let counter on the way
						for (Cell wayPoint : figure.way(dist)) { //array of way points
							for (Figure busy : existFigures) { // array of existing figures
								if (wayPoint.getX() == busy.position.getX() && wayPoint.getY() == busy.position.getY()) { //Check way points
									let++; //let count
								}
							}
						}
						if (let == 0 || figure.getClass().toString().equals("class ru.apermyakov.testtask.Horse")) { //if we have't lets on our way or we are horse, then true
							opportunity = true;
							this.figures[indexOfFigures] = figure.clone(dist); //re-record the position of the figure in our array of figures
						} else {
							throw new OccupiedWayException("Occupied way"); //Occupied way
						}
					} else {
						indexOfPossible++; //counter of array steps
					}
					if (indexOfPossible == figure.possibleMoves().length) { //if have passed all array
						throw new ImpossibleMoveException("Impossible to move"); //Impossible move
					}
				}
			} else {
				indexOfFigures++; //counter of array steps
			}
			if (indexOfFigures == existFigures.length) { //if have passed all array
				throw new FigureNotFoundException("Figure not found"); //Figure not found
			}
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