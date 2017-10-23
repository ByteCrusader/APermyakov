package ru.apermyakov.testtask;

import java.util.Arrays;

/**
* Class for modulate chess figure horse.
*
* @author apermyakov
* @version 1.0
* @since 20.10.2017
*/
public class Horse extends Figure {

	/**
	* Initial horse possible moves array.
	*/
	private Cell[] moves = new Cell[8];

	/**
	* Initial figure's move.
	*/
	private Move move = new Move();

	/**
	* Method for initial horse possible moves array.
	*
	* @author apermyakov
	* @since 20.10.2017
	*/
	private void init() {
		if (super.position.getX() + 2 < 8) {
			if (super.position.getY() + 1 < 8) {
				moves[super.reallMoves++] = move.upRight(super.position, 2, 1);
				moves[super.reallMoves++] = move.upRight(super.position, 1, 2);
			}
			if (super.position.getY() - 1 >= 0) {
				moves[super.reallMoves++] = move.downRight(super.position, 2, 1);
				moves[super.reallMoves++] = move.downRight(super.position, 1, 2);
			}
		}
		if (super.position.getX() + 2 >= 8 && super.position.getX() + 1 < 8) {
			if (super.position.getY() + 2 < 8) {
				moves[super.reallMoves++] = move.upRight(super.position, 1, 2);
			}
			if (super.position.getY() - 2 >= 0) {
				moves[super.reallMoves++] = move.downRight(super.position, 1, 2);
			}
		}
		if (super.position.getX() - 2 >= 0) {
			if (super.position.getY() + 1 < 8) {
				moves[super.reallMoves++] = move.upLeft(super.position, 2, 1);
				moves[super.reallMoves++] = move.upLeft(super.position, 1, 2);
			}
			if (super.position.getY() - 1 >= 0) {
				moves[super.reallMoves++] = move.downLeft(super.position, 2, 1);
				moves[super.reallMoves++] = move.downLeft(super.position, 1, 2);
			}
		}
		if (super.position.getX() - 2 < 0 && super.position.getX() - 1 >= 0) {
			if (super.position.getY() + 2 < 8) {
				moves[super.reallMoves++] = move.upLeft(super.position, 1, 2);
			}
			if (super.position.getY() - 2 >= 0) {
				moves[super.reallMoves++] = move.downLeft(super.position, 1, 2);
			}
		}
	}

	/**
	* Design horse.
	*
	* @author apermyakov
	* @param position dispatch of the horse
	* @since 20.10.2017
	*/
	Horse(Cell position) {
		super(position);
		this.init();
	}

	/**
	* Method for initial horse possible moves array.
	*
	* @author apermyakov
	* @return array of possible cells
	* @since 20.10.2017
	*/
	public Cell[] possibleMoves() {
		return Arrays.copyOf(moves, super.reallMoves);
	}

	/**
	* Method for calculate horse way to possible cell.
	*
	* @author apermyakov
	* @param dist destination
	* @return array of way cells
	* @since 20.10.2017
	*/
	public Cell[] way(Cell dist) {
		Cell[] horseWay = new Cell[64];
		int wayPoints = 0;
		int wayLength = 0;
		for (Cell option : Arrays.copyOf(moves, super.reallMoves)) {
			if (dist.getX() == option.getX() && dist.getY() == option.getY()) {
				int wayXLength = Math.max(dist.getX(), super.position.getX()) - Math.min(dist.getX(), super.position.getX());
				int wayYLength = Math.max(dist.getY(), super.position.getY()) - Math.min(dist.getY(), super.position.getY());
				wayLength = wayXLength + wayYLength;
				if (wayYLength > wayXLength) { //first Y or X
					if (dist.getY() - super.position.getY() > 0) { //first Y and up
						for (int index = 1; index <= wayYLength; index++) { //going up
							horseWay[wayPoints++] = move.upRight(super.position, 0, index);
						}
					} else { //first Y and down
						for (int index = 1; index <= wayYLength; index++) { //going down
							horseWay[wayPoints++] = move.downLeft(super.position, 0, index);
						}
					}
				} else {
					if (dist.getX() - super.position.getX() > 0) { //first X and right
						for (int index = 1; index <= wayXLength; index++) { //going right
							horseWay[wayPoints++] = move.upRight(super.position, index, 0);
						}
					} else { //first X and left
						for (int index = 1; index <= wayXLength; index++) { //going left
							horseWay[wayPoints++] = move.downLeft(super.position, index, 0);
						}
					}
				}
				horseWay[wayPoints++] = new Cell(dist.getX(), dist.getY()); //final point
				break;
			}
		}
		return Arrays.copyOf(horseWay, wayPoints);
	}

	/**
	* Method for clone horse into new cell.
	*
	* @author apermyakov
	* @param dist destination
	* @return new Horse
	* @since 20.10.2017
	*/
	public Figure clone(Cell dist) {
		return new Horse(dist);
	}
}