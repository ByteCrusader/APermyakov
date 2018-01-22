package ru.apermyakov;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class for sort list of users.
 *
 * @author apermyakov
 * @version 1.0
 * @since 24.10.2017
 */
public class SortUser {

    /**
     * Method for sort users and convert unsorted list into sorted set.
     *
     * @param list unsorted list
     * @return sorted set
     */
    public Set<User> sort(List<User> list) {
        return list.stream().filter(Objects::nonNull).collect(Collectors.toSet());
    }

    /**
     * Method for sort users by name length.
     *
     * @param users unsorted list of users
     * @return sorted list of users
     */
    public List<User> sortNameLength(List<User> users) {
        users.sort((o1, o2) -> o1 == null ? 1
                : o2 == null ? -1
                : compareLength(o1, o2));
        return users;
    }

    /**
     * Method for sort users by name length and age.
     *
     * @param users unsorted list of users
     * @return sorted list of users
     */
    public List<User> sortByAllFields(List<User> users) {
        users.sort((o1, o2) -> o1 == null ? 1
                : o2 == null ? -1
                : compareLength(o1, o2) != 0 ? compareLength(o1, o2)
                : o1.getAge().compareTo(o2.getAge()));
        return users;
    }

    /**
     * Method for compare user's names.
     *
     * @param o1 first user
     * @param o2 second user
     * @return sorted names by length
     */
    public int compareLength(User o1, User o2) {
        return Integer.compare(o1.getName().length(), o2.getName().length());
    }
}
