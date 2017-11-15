package ru.apermyakov.threads;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class for test words and spaces.
 *
 * @author apermyakov
 * @version 1.0
 * @since 15.11.2017
 */
public class WordsASpacesTest {

    /**
     * Test when calculate char number then user see receptionist.
     */
    @Test
    public void whenCalculateCharNumberThenUserSeeReceptionist() {
        WordsASpaces.calculate("Hi, Petr, I work fine!");
        WordsASpaces.calculate("Hi, Petr, bye!");
        WordsASpaces.calculate("   Hi,   Petr, threads   is awesome idea!   ");
    }
}