package ru.apermyakov;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class for convert user into list to hash map.
 *
 * @author apermyakov
 * @version 1.0
 * @since 23.10.2017
 */
public class UserConvert {

    /**
     * Method for convert list to hash map.
     *
     * @param list list of users
     * @return hash map of users
     */
    public Map<Integer, User> process(List<User> list) {
        return list.stream().collect(Collectors.toMap(
                User::getId,
                p -> p
        ));
    }
}
