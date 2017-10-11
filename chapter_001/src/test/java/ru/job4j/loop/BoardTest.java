package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test chess board.
 *
 * @author apermyakov
 * @since 11.10.2017
 * @version 1.0
 */
public class BoardTest {

    /**
     * Test when draw chess board with width three and height three then take string three three.
     *
     * @since 11.10.2017
     */
    @Test
    public void whenDrawChessBoardWithWidthThreeAndHeightThreeThenTakeStringThreeThree() {
        Board board = new Board();
        String result = board.paint(3, 3);
        String enter = System.getProperty("line.separator");
        String expected = String.format("x x%s x %sx x%s", enter, enter, enter);
        assertThat(result, is(expected));
    }

    /**
     * Test when draw chess board with width five and height four then take string five four.
     *
     * @since 11.10.2017
     */
    @Test
    public void whenDrawChessBoardWithWidthFiveAndHeightFourThenTakeStringFiveFour() {
        Board board = new Board();
        String result = board.paint(5, 4);
        String enter = System.getProperty("line.separator");
        String expected = String.format("x x x%s x x %sx x x%s x x %s", enter, enter, enter, enter);
        assertThat(result, is(expected));
    }
}
