package ru.apermyakov.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;

/**
 * Class for modulate synch user storage.
 *
 * @author apermyakov
 * @version 1.0
 * @since 16.11.2017
 */
public class SynchUserStorage {

    /**
     * Class for modulate user.
     *
     * @author apermyakov
     * @version 1.0
     * @since 16.11.2017
     */
    public static class User {

        /**
         * Field for user id.
         */
        int id;

        /**
         * Field for user amount.
         */
        int amount;

        /**
         * Design user.
         *
         * @param id user id
         * @param amount user amount
         */
        public User(int id, int amount) {
            this.id = id;
            this.amount = amount;
        }

        /**
         * Method for override to string.
         *
         * @return user string
         */
        @Override
        public String toString() {
            return "User " +
                    "id = " + id +
                    ", amount = " + amount;
        }
    }

    /**
     * Class for modulate thread save user storage.
     *
     * @author apermyakov
     * @version 1.0
     * @since 16.11.2017
     */
    @ThreadSafe
    public static class UserStorage {

        /**
         * Field for user's container.
         */
        @GuardedBy("this")
        final HashMap<Integer, User> container = new HashMap<>();

        /**
         * Method for add user to container.
         *
         * @param user user
         * @return add or not
         */
        public boolean add(final User user) {
            synchronized (this) {
                if (user != null) {
                    container.put(user.id, user);
                }
                return user != null;
            }
        }

        /**
         * Method for update user to container.
         *
         * @param user user
         * @return update or not
         */
        public boolean update(final User user) {
            synchronized (this) {
                return user != null && container.containsKey(user.id) && add(user);
            }
        }

        /**
         * Method for delete user from container.
         *
         * @param user user
         * @return delete or not
         */
        public boolean delete(final User user) {
            synchronized (this) {
                boolean result = false;
                if (user != null && container.containsKey(user.id)) {
                    container.remove(user.id);
                    result = true;
                }
                return result;
            }
        }

        /**
         * Method for transfer amount from first user to second user.
         *
         * @param fromId first user
         * @param toId second user
         * @param amount amount
         * @return transfer or not
         */
        public boolean transfer(final int fromId, final int toId, final int amount) {
            synchronized (this) {
                boolean result = false;
                if (container.containsKey(fromId) && container.containsKey(toId)) {
                    int amountFirstUser = container.get(fromId).amount;
                    int amountSecondUser = container.get(toId).amount;
                    if (amountFirstUser >= amount) {
                        add(new User(fromId, amountFirstUser - amount));
                        add(new User(toId, amountSecondUser + amount));
                        System.out.println(String.format("Transfer result : \n%s \n%s",
                                container.get(fromId), container.get(toId)));
                        result = true;
                    }
                }
                return result;
            }
        }
    }

    /**
     * Main method.
     *
     * @param args args
     */
    public static void main(String[] args) {

        UserStorage storage = new UserStorage();

        Thread first = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(String.format("Add first by first thread - %s", storage.add(new User(1, 100))));
                System.out.println(String.format("Add second by first thread - %s", storage.add(new User(2, 200))));

                System.out.println(String.format("Transfer by first thread - %s", storage.transfer(1, 2, 50)));
            }
        }
        );

        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(String.format("Add third by second thread - %s", storage.add(new User(3, 30))));

                System.out.println(String.format("Delete third by second thread - %s", storage.delete(new User(3, 30))));

                System.out.println(String.format("Update first by second thread - %s", storage.update(new User(1, 500))));
            }
        }
        );

        System.out.println(String.format("Add null by main - %s", storage.add(null)));
        System.out.println(String.format("Update null by main - %s", storage.update(null)));
        System.out.println(String.format("Delete null by main - %s", storage.delete(null)));
        System.out.println(String.format("Transfer out of container user by main - %s", storage.transfer(10, 20, 50)));

        first.start();
        second.start();
    }
}
