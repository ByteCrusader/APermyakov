package ru.apermyakov.maprealize;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test simple hash map.
 *
 * @author apermyakov
 * @version 1.0
 * @since 09.11.2017
 */
public class SimpleHashMapTest {

    /**
     * Test when add item in map then without collisions.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAddItemInMapThenWithoutCollisions() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        assertThat(map.insert(new Node("First", "First")), is(true));
        assertThat(map.insert(new Node("Second", "Second")), is(true));
        assertThat(map.insert(new Node("Third", "Third")), is(true));
        assertThat(map.insert(new Node("First", "Fourth")), is(false)); //collision
        assertThat(map.insert(new Node("First", "First")), is(false)); //collision
        assertThat(map.get("First"), is("First"));
        assertThat(map.get("Second"), is("Second"));
        assertThat(map.get("Third"), is("Third"));
		map.iterator().next();
		assertThat(map.iterator().hasNext(), is(true));
		map.iterator().next();
		assertThat(map.iterator().hasNext(), is(true));
		map.iterator().next();
		assertThat(map.iterator().hasNext(), is(false));
		assertThat(map.insert(new Node("four", "four")), is(true));
		assertThat(map.insert(new Node("five", "five")), is(false)); //collision
		assertThat(map.insert(new Node("six", "six")), is(true));
		assertThat(map.insert(new Node("seven", "seven")), is(false)); //collision
		assertThat(map.insert(new Node("eight", "eight")), is(true));
		assertThat(map.insert(new Node("nine", "nine")), is(true));
		assertThat(map.insert(new Node("ten", "ten")), is(true));
		assertThat(map.insert(new Node("eleven", "eleven")), is(false)); //collision
		assertThat(map.insert(new Node("twelve", "twelve")), is(false)); //collision
		assertThat(map.insert(new Node("thirteen", "thirteen")), is(false)); //collision
		assertThat(map.insert(new Node("fourteen", "fourteen")), is(false)); //collision
		assertThat(map.insert(new Node("fifteen", "fifteen")), is(true));
		assertThat(map.insert(new Node("sixteen", "sixteen")), is(true));
		assertThat(map.insert(new Node("seventeen", "seventeen")), is(false)); //collision
		assertThat(map.insert(new Node("eighteen", "eighteen")), is(true));
		assertThat(map.insert(new Node("nineteen", "nineteen")), is(false)); //collision
		assertThat(map.insert(new Node("twenty", "twenty")), is(false)); //collision
		assertThat(map.delete("First"), is(true));
        map.get("First");
    }
	
	    /**
     * Test when add item in increase map then without collisions.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAddItemInIncreaseMapThenWithoutCollisions() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
		assertThat(map.largeMap(), is(160));
        assertThat(map.insert(new Node("First", "First")), is(true));
        assertThat(map.insert(new Node("Second", "Second")), is(true));
        assertThat(map.insert(new Node("Third", "Third")), is(true));
        assertThat(map.insert(new Node("First", "Fourth")), is(false)); //collision
        assertThat(map.insert(new Node("First", "First")), is(false)); //collision
        assertThat(map.get("First"), is("First"));
        assertThat(map.get("Third"), is("Third"));
		map.iterator().next();
		assertThat(map.iterator().hasNext(), is(true));
		map.iterator().next();
		assertThat(map.iterator().hasNext(), is(true));
		map.iterator().next();
		assertThat(map.iterator().hasNext(), is(false));
		assertThat(map.insert(new Node("four", "four")), is(true));
		assertThat(map.insert(new Node("five", "five")), is(true));
		assertThat(map.insert(new Node("six", "six")), is(true));
		assertThat(map.insert(new Node("seven", "seven")), is(true));
		assertThat(map.insert(new Node("eight", "eight")), is(true));
		assertThat(map.insert(new Node("nine", "nine")), is(true));
		assertThat(map.insert(new Node("ten", "ten")), is(true));
		assertThat(map.insert(new Node("eleven", "eleven")), is(false)); //collision
		assertThat(map.insert(new Node("twelve", "twelve")), is(true)); //collision
		assertThat(map.insert(new Node("thirteen", "thirteen")), is(true));
		assertThat(map.insert(new Node("fourteen", "fourteen")), is(false)); //collision
		assertThat(map.insert(new Node("fifteen", "fifteen")), is(true));
		assertThat(map.insert(new Node("sixteen", "sixteen")), is(true));
		assertThat(map.insert(new Node("seventeen", "seventeen")), is(true));
		assertThat(map.insert(new Node("eighteen", "eighteen")), is(true));
		assertThat(map.insert(new Node("nineteen", "nineteen")), is(true));
		assertThat(map.insert(new Node("twenty", "twenty")), is(false)); //collision
		assertThat(map.delete("Third"), is(true));
        map.get("Third");
    }
}