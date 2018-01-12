package ru.apermyakov.testtask.board;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for test board.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public class BoardTest {

    /**
     * Field for board.
     */
    private Board board;

    /**
     * Initial board.
     */
    @Before
    public void initial() {
        this.board = new GameBoard(3, 3);
        board.initialBoard();
    }

    /**
     * Test when build full board then get full board.
     */
    @Test
    public void whenBuildFullBoardThenGetFullBoard() {
        board.setValue(1, 1, true);
        board.setValue(2, 2, false);
        board.setValue(1, 2, true);
        board.setValue(1, 3, false);
        board.setValue(3, 1, true);
        board.setValue(2, 1, false);
        board.setValue(2, 3, true);
        board.setValue(3, 2, false);
        board.setValue(3, 3, true);
        assertThat(board.getBoard(), is("X|X|O\r\n-----\r\nO|O|X\r\n-----\r\nX|O|X\r\n"));
    }

    /**
     * Test when set sell to board and select this sell then take this sell.
     */
    @Test
    public void whenSetSellToBoardAndSelectThisSellThenTakeThisSell() {
        board.setValue(1, 1, true);
        assertThat(board.selectSell(1, 1).isCross(), is(true));
    }

    /**
     * Test when get all sell from board then get correct.
     */
    @Test
    public void whenGetAllSellFromBoardThenGetCorrect() {
        board.setValue(1, 3, false);
        board.setValue(3, 1, true);
        board.setValue(2, 1, false);
        board.setValue(2, 3, true);
        assertThat(board.getSells().get(2).isCross(), is(false));
    }
}