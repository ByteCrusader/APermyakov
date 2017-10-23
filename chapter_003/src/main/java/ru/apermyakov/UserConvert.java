package ru.apermyakov;

import java.util.HashMap;
import java.util.List;

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
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User user : list) {
            result.put(user.getId(), user);
        }
        return result;
    }
}
