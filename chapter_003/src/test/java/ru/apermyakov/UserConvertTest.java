package ru.apermyakov;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for test convert list of users to hash map.
 *
 * @author apermyakov
 * @version 1.0
 * @since 23.10.2017
 */
public class UserConvertTest {

    /**
     * Test when list of users has one user then hash map has this user.
     */
    @Test
    public void whenListOfUsersHasOneUserThenHashMapHasThisUser() {
        User user = new User(15, "Alex", "Ekb");
        List<User> users = new ArrayList<>();
        users.add(user);
        UserConvert convert = new UserConvert();
        HashMap<Integer, User> result = convert.process(users);
        assertThat(result.containsKey(user.getId()), is(true));
    }

    /**
     * Test when list of users has three users then hash map has third user.
     */
    @Test
    public void whenListOfUsersHasThreeUsersThenHashMapHasThirdUser() {
        User first = new User(15, "Alex", "Ekb");
        User second = new User(16, "Ivan", "Msc");
        User third = new User(17, "Petr", "Bryansk");
        List<User> users = new ArrayList<>();
        users.add(first);
        users.add(second);
        users.add(third);
        UserConvert convert = new UserConvert();
        HashMap<Integer, User> result = convert.process(users);
        assertThat(result.containsValue(third), is(true));
    }
}
