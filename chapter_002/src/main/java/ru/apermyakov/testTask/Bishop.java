package ru.apermyakov.testtask;

import java.util.Arrays;

/**
* Class for modulate chess figure bishop.
*
* @author apermyakov
* @version 1.0
* @since 20.10.2017
*/
public class Bishop extends Figure {

	/**
	* Initial bishop possible moves array.
	*/
	private Cell[] moves = new Cell[13];

	/**
	* Method for initial bishop possible moves array.
	*
	* @author apermyakov
	* @since 20.10.2017
	*/
	private void init() {
		for (int count = 1;
			super.position.getX() + count < 8 &&  super.position.getY() + count < 8;
			count++) {
			moves[super.reallMoves++] = new Cell(super.position.getX() + count, super.position.getY() + count);
		}
		for (int count = 1;
			super.position.getX() + count < 8 &&  super.position.getY() - count >= 0;
			count++) {
			moves[super.reallMoves++] = new Cell(super.position.getX() + count, super.position.getY() - count);
		}
		for (int count = 1;
			super.position.getX() - count >= 0 &&  super.position.getY() - count >= 0;
			count++) {
			moves[super.reallMoves++] = new Cell(super.position.getX() - count, super.position.getY() - count);
		}
		for (int count = 1;
			super.position.getX() - count >= 0 &&  super.position.getY() + count < 8;
			count++) {
			moves[super.reallMoves++] = new Cell(super.position.getX() - count, super.position.getY() + count);
		}
	}

	/**
	* Design bishop.
	*
	* @author apermyakov
	* @param position dispatch of the bishop
	* @since 20.10.2017
	*/
	Bishop(Cell position) {
		super(position);
		this.init();
	}

	/**
	* Method for initial bishop possible moves array.
	*
	* @author apermyakov
	* @return array of possible cells
	* @since 20.10.2017
	*/
	public Cell[] possibleMoves() {
		return Arrays.copyOf(moves, super.reallMoves);
	}

	/**
	* Method for calculate bishop way to possible cell.
	*
	* @author apermyakov
	* @param dist destination
	* @return array of way cells
	* @since 20.10.2017
	*/
	public Cell[] way(Cell dist) {
		Cell[] bishopWay = new Cell[64];
		int wayPoints = 0;
		int wayLength = 0;
		for (Cell option : Arrays.copyOf(moves, super.reallMoves)) {
			if (dist.getX() == option.getX() && dist.getY() == option.getY()) {
				wayLength = Math.max(dist.getX(), super.position.getX()) - Math.min(dist.getX(), super.position.getX());
				if (dist.getX() > super.position.getX() && dist.getY() > super.position.getY()) { //up and right
					for (int index = 1; index <= wayLength; index++) {
						bishopWay[wayPoints++] = new Cell(super.position.getX() + index, super.position.getY() + index);
					}
				} else if (dist.getX() > super.position.getX() && dist.getY() < super.position.getY()) { //down and right
					for (int index = 1; index <= wayLength; index++) {
						bishopWay[wayPoints++] = new Cell(super.position.getX() + index, super.position.getY() - index);
					}
				} else if (dist.getX() < super.position.getX() && dist.getY() < super.position.getY()) { //down and left
					for (int index = 1; index <= wayLength; index++) {
						bishopWay[wayPoints++] = new Cell(super.position.getX() - index, super.position.getY() - index);
					}
				} else { //up and left
					for (int index = 1; index <= wayLength; index++) {
						bishopWay[wayPoints++] = new Cell(super.position.getX() - index, super.position.getY() + index);
					}
				}
			}
		}
		return Arrays.copyOf(bishopWay, wayPoints);
	}

	/**
	* Method for clone bishop into new cell.
	*
	* @author apermyakov
	* @param dist destination
	* @return new bishop
	* @since 20.10.2017
	*/
	public Figure clone(Cell dist) {
		return new Bishop(dist);
	}
}