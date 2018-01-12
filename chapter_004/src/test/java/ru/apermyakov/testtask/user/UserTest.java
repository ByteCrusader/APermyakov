package ru.apermyakov.testtask.user;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test user.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public class UserTest {

    /**
     * Field for user.
     */
    private User user;

    /**
     * Initial user.
     */
    @Before
    public void initial() {
        this.user = new BoardUser("Petr", true);
        user.setValueCross();
        user.setPriority(2);
    }

    /**
     * Test when get name then take Petr.
     */
    @Test
    public void whenGetNameThenTakePetr() {
        assertThat(this.user.getName(), is("Petr"));
    }

    /**
     * Test when get priority then take two.
     */
    @Test
    public void whenGetPriorityThenTakeTwo() {
        assertThat(this.user.getPriority(), is(2));
    }

    /**
     * Test when check is user than take true.
     */
    @Test
    public void whenCheckIsUserThanTakeTrue() {
        assertThat(this.user.isUser(), is(true));
    }

    /**
     * Test when check is cross than take true.
     */
    @Test
    public void whenCheckIsCrossThanTakeTrue() {
        assertThat(this.user.isCross(), is(true));
    }
}