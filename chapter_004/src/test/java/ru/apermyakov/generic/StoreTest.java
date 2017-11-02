package ru.apermyakov.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test store.
 *
 * @author apermyakov
 * @version 1.0
 * @since 01.11.2017
 */
public class StoreTest {

    /**
     * Test when check all method of user store then assert.
     */
    @Test
    public void whenCheckAllMethodOfUserStoreThenAssert() {
        AbstractStore<User> abstractStore = new UserStore();
        User user1 = new User("Petr", "Male", 30);
        User user2 = new User("Ivan", "Male", 15);
        User user3 = new User("Vasilii", "Male", 47);
        assertThat(abstractStore.add(user1), is(user1));
        abstractStore.add(user2);
        user3.setId(user2.getId());
        assertThat(abstractStore.update(user3), is(user3));
        assertThat(abstractStore.delete(user2.getId()), is(true));
    }

    /**
     * Test when check all method of role store then assert.
     */
    @Test
    public void whenCheckAllMethodOfRoleStoreThenAssert() {
        AbstractStore<Role> abstractStore = new RoleStore();
        Role role1 = new Role("Junior", 0D);
        Role role2 = new Role("Middle", 1D);
        Role role3 = new Role("Senior", 4D);
        assertThat(abstractStore.add(role1), is(role1));
        abstractStore.add(role2);
        role3.setId(role2.getId());
        assertThat(abstractStore.update(role3), is(role3));
        assertThat(abstractStore.delete(role1.getId()), is(true));
    }

    /**
     * Test when update out of container data then exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenUpdateOutOfContainerDataThenException() {
        AbstractStore<User> abstractStore = new UserStore();
        User user1 = new User("Petr", "Male", 30);
        User user2 = new User("Ivan", "Male", 15);
        User user3 = new User("Vasilii", "Male", 47);
        abstractStore.add(user1);
        abstractStore.add(user2);
        assertThat(abstractStore.update(user3), is(user3));
    }

    /**
     * Test when delete out of container data then exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenDeleteOutOfContainerDataThenException() {
        AbstractStore<Role> abstractStore = new RoleStore();
        Role role1 = new Role("Junior", 0D);
        Role role2 = new Role("Middle", 1D);
        Role role3 = new Role("Senior", 4D);
        abstractStore.add(role1);
        abstractStore.add(role2);
        abstractStore.delete(role3.getId());
    }
}