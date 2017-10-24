package ru.apermyakov;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for test sort user.
 *
 * @author apermyakov
 * @version 1.0
 * @since 24.10.2017
 */
public class SortUserTest {

    /**
     * Test when unsorted list of three users when cast sorted set of three users.
     */
    @Test
    public void whenUnsortedListOfThreeUsersWhenCastSortedSetOfThreeUsers() {
        List<User> usersList = new ArrayList<>();
        usersList.addAll(
                Arrays.asList(
                        new User("Vasilii", "35"),
                        new User("Ivan", "50"),
                        new User("Eugen", "21")
                )
        );
        SortUser convert = new SortUser();
        Set<User> result = new TreeSet<>();
        result.addAll(convert.sort(usersList));
        assertThat(result.toString(), is("["
                + "User{, name='Eugen', age='21'}, "
                + "User{, name='Vasilii', age='35'}, "
                + "User{, name='Ivan', age='50'}"
                + "]"));
    }

    /**
     * Test when unsorted list of five users and null where two users have one age when cast sorted set of five users.
     */
    @Test
    public void whenUnsortedListOfFiveUsersAndNullWhereTwoUsersHaveOneAgeWhenCastSortedSetOfFiveUsers() {
        List<User> usersList = new ArrayList<>();
        usersList.addAll(
                Arrays.asList(
                        new User("Vasilii", "35"),
                        new User("Ivan", "50"),
                        new User("Eugen", "21"),
                        new User("Mike", "21"),
                        null,
                        new User("Kirk", "81")
                )
        );
        SortUser convert = new SortUser();
        Set<User> result = new TreeSet<>();
        result.addAll(convert.sort(usersList));
        assertThat(result.toString(), is("["
                + "User{, name='Eugen', age='21'}, "
                + "User{, name='Mike', age='21'}, "
                + "User{, name='Vasilii', age='35'}, "
                + "User{, name='Ivan', age='50'}, "
                + "User{, name='Kirk', age='81'}]"));
    }

    /**
     * Test when unsorted list of three users when cast sorted set of three users by name length.
     */
    @Test
    public void whenUnsortedListOfThreeUsersWhenCastSortedListOfThreeUsersByNameLength() {
        List<User> usersList = new ArrayList<>();
        usersList.addAll(
                Arrays.asList(
                        new User("Vasilii", "35"),
                        new User("Ivan", "50"),
                        new User("Eugen", "21")
                )
        );
        SortUser convert = new SortUser();
        List<User> result = new ArrayList<>();
        result.addAll(convert.sortNameLength(usersList));
        assertThat(result.toString(), is("["
                + "User{, name='Ivan', age='50'}, "
                + "User{, name='Eugen', age='21'}, "
                + "User{, name='Vasilii', age='35'}"
                + "]"));
    }

    /**
     * Test when unsorted list of three users with null when cast sorted set of three users by name length.
     */
    @Test
    public void whenNullUnsortedListOfThreeUsersWithNullWhenCastSortedListOfThreeUsersByNameLength() {
        List<User> usersList = new ArrayList<>();
        usersList.addAll(
                Arrays.asList(
                        null,
                        new User("Ivan", "50"),
                        new User("Eugen", "21")
                )
        );
        SortUser convert = new SortUser();
        List<User> result = new ArrayList<>();
        result.addAll(convert.sortNameLength(usersList));
        assertThat(result.toString(), is("["
                + "User{, name='Ivan', age='50'}, "
                + "User{, name='Eugen', age='21'}, "
                + "null"
                + "]"));
    }

    /**
     * Test when unsorted list of six users when cast sorted list of six users by all fields.
     */
    @Test
    public void whenUnsortedListOfSixUsersWhenCastSortedListOfSixUsersByAllFields() {
        List<User> usersList = new ArrayList<>();
        usersList.addAll(
                Arrays.asList(
                        new User("Vasillii", "63"),
                        new User("Ivan", "50"),
                        new User("Ivan", "45"),
                        new User("Eugen", "21"),
                        new User("Pafnytii", "69"),
                        new User("Pafnytii", "60")
                )
        );
        SortUser convert = new SortUser();
        List<User> result = new ArrayList<>();
        result.addAll(convert.sortByAllFields(usersList));
        assertThat(result.toString(), is("["
                + "User{, name='Ivan', age='45'}, "
                + "User{, name='Ivan', age='50'}, "
                + "User{, name='Eugen', age='21'}, "
                + "User{, name='Pafnytii', age='60'}, "
                + "User{, name='Vasillii', age='63'}, "
                + "User{, name='Pafnytii', age='69'}"
                + "]"));
    }

    /**
     * Test when unsorted list of three users when cast sorted set of three users.
     */
    @Test
    public void whenNullUnsortedListOfSixUsersWithNullWhenCastSortedListOfSixUsersByAllField() {
        List<User> usersList = new ArrayList<>();
        usersList.addAll(
                Arrays.asList(
                        null,
                        new User("Ivan", "50"),
                        new User("Ivan", "45"),
                        new User("Eugen", "21"),
                        new User("Pafnytii", "69"),
                        new User("Pafnytii", "60")
                )
        );
        SortUser convert = new SortUser();
        List<User> result = new ArrayList<>();
        result.addAll(convert.sortByAllFields(usersList));
        assertThat(result.toString(), is("["
                + "User{, name='Ivan', age='45'}, "
                + "User{, name='Ivan', age='50'}, "
                + "User{, name='Eugen', age='21'}, "
                + "User{, name='Pafnytii', age='60'}, "
                + "User{, name='Pafnytii', age='69'}, "
                + "null"
                + "]"));
    }
}
