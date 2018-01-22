package ru.apermyakov.testtask;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Сlass for test chess board.
*
* @author apermyakov
* @version 1.0
* @since 20.10.2017
*/
public class BoardTest {

	/**
	* Test when Horse on a cell D5 and go to E7 is true.
	*/
	@Test
	public void whenHorseOnACellD5ThenGoCellE7() {
		Board board = new Board();
		Cell first = new Cell("D", 5);
		board.initial(new Horse(first));
		Cell second = new Cell("E", 7);
		boolean result = board.move(first, second);
		assertThat(result, is(true));
	}

	/**
	* Test when Horse on a cell D5 and go to E7 then in our array of figures re-record our Horse into E7.
	*/
	@Test
	public void whenHorseOnACellD5ThenGoCellE7AndNewHorse() {
		Board board = new Board();
		Cell first = new Cell("D", 5);
		board.initial(new Horse(first));
		Cell second = new Cell("E", 7);
		boolean result = board.move(first, second);
		assertThat(board.getFigures()[0].position.getX(), is(second.getX()));
	}

	/**
	* Test when Horse on a cell D5 and go to E3 is true.
	*/
	@Test
	public void whenHorseOnACellD5ThenGoE3() {
		Board board = new Board();
		Cell first = new Cell("D", 5);
		board.initial(new Horse(first));
		Cell second = new Cell("E", 3);
		boolean result = board.move(first, second);
		assertThat(result, is(true));
	}

	/**
	* Test when Horse on a cell D5 and go to b4 is true.
	*/
	@Test
	public void whenHorseOnACellD5ThenGoB4() {
		Board board = new Board();
		Cell first = new Cell("D", 5);
		board.initial(new Horse(first));
		Cell second = new Cell("B", 4);
		boolean result = board.move(first, second);
		assertThat(result, is(true));
	}

	/**
	* Test when Horse on a cell D5, Horse on D6 and go to E7 is true.
	*/
	@Test
	public void whenHorseOnACellD5AndSomeFigureOnTheWayThenGoF6() {
		Board board = new Board();
		Cell first = new Cell("D", 5);
		board.initial(new Horse(first));
		board.initial(new Horse(new Cell("D", 6)));
		Cell second = new Cell("E", 7);
		boolean result = board.move(first, second);
		assertThat(result, is(true));
	}

	/**
	* Test when Horse on a cell D6 but we need to move D5 to E7 then take exceprion.
	*/
	@Test(expected = FigureNotFoundException.class)
	public void whenHorseNoOnACellD5ThenException() {
		Board board = new Board();
		board.initial(new Horse(new Cell("D", 6)));
		Cell first = new Cell("D", 5);
		Cell second = new Cell("E", 7);
		boolean result = board.move(first, second);
	}

	/**
	* Test when Horse on a cell D5 and check move to A1 then take exceprion.
	*/
	@Test(expected = ImpossibleMoveException.class)
	public void whenHorseOnACellD5ButWrongCellThenException() {
		Board board = new Board();
		board.initial(new Horse(new Cell("D", 5)));
		Cell first = new Cell("D", 5);
		Cell second = new Cell("A", 1);
		boolean result = board.move(first, second);
	}

	/**
	* Test when bishop on a cell D5 then G6 is possible move.
	*/
	@Test
	public void whenBishopOnACellD5ThenGoCellG6() {
		Board board = new Board();
		Cell first = new Cell("D", 5);
		Bishop bishop = new Bishop(first);
		Cell second = new Cell("G", 8);
		assertThat(bishop.possibleMoves()[9].getX(), is(second.getX()));
	}

	/**
	* Test when bishop on a cell D5 and go to E6 then have one way point.
	*/
	@Test
	public void whenBishopOnACellD5AndGoCellE6ThenHaveOneWayPoint() {
		Board board = new Board();
		Cell first = new Cell("D", 5);
		Bishop bishop = new Bishop(first);
		Cell second = new Cell("E", 6);
		assertThat(bishop.way(second).length, is(1));
	}

	/**
	* Test when bishop on a cell D5 and go to E6 then true.
	*/
	@Test
	public void whenBishopOnACellD5AndGoCellE6ThenTrue() {
		Board board = new Board();
		Cell first = new Cell("D", 5);
		board.initial(new Bishop(first));
		Cell second = new Cell("E", 6);
		boolean result = board.move(first, second);
		assertThat(result, is(true));
	}

	/**
	* Test when bishop on a cell D5 and another bishop on B6 and go A7 then exception.
	*/
	@Test(expected = OccupiedWayException.class)
	public void whenBishopOnACellD5AnotherBishopOnB6AndGoCellA7ThenException() {
		Board board = new Board();
		Cell first = new Cell("D", 5);
		board.initial(new Bishop(first));
		Cell second = new Cell("B", 7);
		board.initial(new Bishop(second));
		Cell third = new Cell("A", 8);
		boolean result = board.move(first, third);
	}
}