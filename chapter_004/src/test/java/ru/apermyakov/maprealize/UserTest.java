package ru.apermyakov.maprealize;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for test user activity.
 *
 * @author apermyakov
 * @version 1.0
 * @since 08.11.2017
 */
public class UserTest {

    /**
     * Test when add to map equal items then added onle last equal item.
     */
    @Test
    public void whenAddToMapEqualItemsThenAddedOnleLastEqualItem() {
        Calendar data = new GregorianCalendar(1955, Calendar.DECEMBER, 31);
        User first = new User("Ivan", 2, data);
        User second = new User("Ivan", 2, data);
        User third = new User("Ivan", 2, data);
        User fourth = new User("Vasilii", 2, data);
        Map<User, Object> test = new HashMap<>();
        test.put(first, "first");
        test.put(second, "second");
        test.put(third, "third");
        test.put(fourth, "fourth");
        int i = (int) 'g';
        System.out.println(i);
        System.out.print(first.hashCode());
        System.out.println(first.equals(second));
        System.out.print(second.hashCode());
        System.out.println(second.equals(third));
        System.out.print(third.hashCode());
        System.out.println(third.equals(fourth));
        System.out.print(fourth.hashCode());
        System.out.println(fourth.equals(first));
        System.out.println(test);
    }
}