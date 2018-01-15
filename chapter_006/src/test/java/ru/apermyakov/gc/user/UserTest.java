package ru.apermyakov.gc.user;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test user.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 15.01.2018.
 */
public class UserTest {

    /**
     * Field for user.
     */
    private User user;

    /**
     * Method for initialize test.
     */
    @Before
    public void initialize(){
        this.user = new CollectorUser("Petr", "M");
        this.user.setAge(25);
    }

    /**
     * Test when get sex then take m.
     */
    @Test
    public void whenGetSexThenTakeM() {
        assertThat(this.user.getSex(), is("M"));
    }

    /**
     * Test when get name then take petr.
     */
    @Test
    public void whenGetNameThenTakePetr() {
        assertThat(this.user.getName(), is("Petr"));
    }

    /**
     * Test when get age then take 25.
     */
    @Test
    public void whenGetAgeThenTake25() {
        assertThat(this.user.getAge(), is(25));
    }
}