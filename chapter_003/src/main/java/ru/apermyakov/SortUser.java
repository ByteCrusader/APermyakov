package ru.apermyakov;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
        Set<User> sorted = new TreeSet<>();
        for (User user : list) {
            if (user != null) {
                sorted.add(user);
            }
        }
        return sorted;
    }
}
