package ru.apermyakov.testtask;

/**
* Abstract class for modulate chess figure.
*
* @author apermyakov
* @version 1.0
* @since 20.10.2017
*/
public abstract class Figure {

	/**
	* Initial figure's position.
	*/
	final Cell position;

	/**
	* Initial figure reall moves caunter.
	*/
	int reallMoves = 0;

	/**
	* Method for initial figure.
	*
	* @author apermyakov
	* @param position figure's position
	* @since 20.10.2017
	*/
	public Figure(Cell position) {
		this.position = position;
	}

	/**
	* Method for search possible moves of figure.
	*
	* @author apermyakov
	* @return array of possible figure's moves
	* @since 20.10.2017
	*/
	abstract Cell[] possibleMoves();

	/**
	* Method for calculate figure's way for some cell.
	*
	* @author apermyakov
	* @param dist destination position of figure
	* @return array of figure's way
	* @since 20.10.2017
	*/
	abstract Cell[] way(Cell dist);

	/**
	* Method for clone figure to some cell.
	*
	* @author apermyakov
	* @param dist destination position of figure
	* @return new figure
	* @since 20.10.2017
	*/
	abstract Figure clone(Cell dist);
}