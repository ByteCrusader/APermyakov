package ru.apermyakov;

import org.junit.Test;

import java.util.*;

/**
 * Class for test time checker.
 *
 * @author apermyakov
 * @version 1.0
 * @since 23.10.2017
 */
public class TimeTest {

    /**
     * Test when add 150 items to ArrayList and delete 83 items.
     */
    @Test
    public void whenAdd150InArrayListItemsAndDelete83ThenTakeTime() {
        List<String> check = new ArrayList<String>();
        Time time = new Time();
        long result = time.add(check, 150);
        System.out.println(String.format("Add 150 items to ArrayList take %s nanosecond.", result));
        result = time.delete(check, 83);
        System.out.println(String.format("Delete 83 items to ArrayList take %s nanosecond.", result));
    }

    /**
     * Test when add 150 items to LinkedList and delete 83 items.
     */
    @Test
    public void whenAdd150InLinkedListItemsAndDelete83ThenTakeTime() {
        List<String> check = new LinkedList<String>();
        Time time = new Time();
        long result = time.add(check, 150);
        System.out.println(String.format("Add 150 items to LinkedList take %s nanosecond.", result));
        result = time.delete(check, 83);
        System.out.println(String.format("Delete 83 items to LinkedList take %s nanosecond.", result));
    }

    /**
     * Test when add 150 items to TreeSet and delete 83 items.
     */
    @Test
    public void whenAdd150InTreeSetItemsAndDelete83ThenTakeTime() {
        Set<String> check = new TreeSet<String>();
        Time time = new Time();
        long result = time.add(check, 150);
        System.out.println(String.format("Add 150 items to TreeSet take %s nanosecond.", result));
        result = time.delete(check, 83);
        System.out.println(String.format("Delete 83 items to TreeSet take %s nanosecond.", result));
    }
}
