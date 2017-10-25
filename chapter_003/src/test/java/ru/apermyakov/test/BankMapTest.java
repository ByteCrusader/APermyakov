package ru.apermyakov.test;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for initial bank map of users.
 *
 * @author apermyakov
 * @version 1.0
 * @since 25.10.2017
 */
public class BankMapTest {

    /**
     * Test when add user and account then map has then user and account.
     */
    @Test
    public void whenAddUserAndAccountThenMapHasThenUserAndAccount() {
        Passport passport = new Passport(6518, 235478, "USSR", "12.02.1978");
        User user = new User("Valerii", passport);
        Account account = new Account(500000, new Requisites(4587521, "Sber", 689752, 87896331, 584789, 123012));
        BankMap list = new BankMap();
        list.addUser(user);
        list.addAccountToUser(user, account);
        assertThat(list.getUserAccount(user).contains(account), is(true));
    }

    /**
     * Test when delete user out of map then exception.
     */
    @Test(expected = NullPointerException.class)
    public void whenDeleteUserOutIfMapThenException() {
        Passport passport = new Passport(6518, 235478, "USSR", "12.02.1978");
        User user = new User("Valerii", passport);
        Passport passportIvana = new Passport(6458, 298378, "USSR", "30.04.1985");
        User userIvan = new User("Ivan", passport);
        Account account = new Account(500000, new Requisites(4587521, "Sber", 689752, 87896331, 584789, 123012));
        BankMap list = new BankMap();
        list.addUser(user);
        list.addAccountToUser(user, account);
        list.deleteUser(userIvan);
    }

    /**
     * Test delete account which user does not have then exception.
     */
    @Test(expected = NullPointerException.class)
    public void whenDeleteAccountWhichUserDoesNotHaveThenException() {
        Passport passport = new Passport(6518, 235478, "USSR", "12.02.1978");
        User user = new User("Valerii", passport);
        Account account = new Account(500000, new Requisites(4587521, "Sber", 689752, 87896331, 584789, 123012));
        Account accountIvana = new Account(150000, new Requisites(4156321, "Sber", 689752, 87878931, 584789, 123012));
        BankMap list = new BankMap();
        list.addUser(user);
        list.addAccountToUser(user, account);
        list.deleteAccountFromUser(user, accountIvana);
    }

    /**
     * Test when add user and three accounts, delete second account then map has not this account.
     */
    @Test
    public void whenAddUserAndThreeAccountsDeleteSecondAccountThenMapHasNotThisAccount() {
        Passport passport = new Passport(6518, 235478, "USSR", "12.02.1978");
        User user = new User("Valerii", passport);
        Account account1 = new Account(500000, new Requisites(4587521, "Sber", 689752, 87896331, 584789, 123012));
        Account account2 = new Account(200000, new Requisites(4587986, "Sber", 689752, 87896784, 584789, 123012));
        Account account3 = new Account(340000, new Requisites(4587127, "Sber", 689752, 87896123, 584789, 123012));
        BankMap list = new BankMap();
        list.addUser(user);
        list.addAccountToUser(user, account1);
        list.addAccountToUser(user, account2);
        list.addAccountToUser(user, account3);
        list.deleteAccountFromUser(user, account2);
        assertThat(list.getUserAccount(user).contains(account2), is(false));
    }

    /**
     * Test when delete user and get his account then exception
     */
    @Test(expected = NullPointerException.class)
    public void whenDeleteUserAndGetHisAccountThenException() {
        Passport passport1 = new Passport(6518, 248478, "USSR", "12.02.1978");
        User user1 = new User("Ivan", passport1);
        Passport passport2 = new Passport(6510, 235469, "USSR", "18.06.1979");
        User user2 = new User("Valerii", passport2);
        Passport passport3 = new Passport(6508, 455478, "USSR", "17.05.1945");
        User user3 = new User("Nikolai", passport3);
        Account account1 = new Account(500000, new Requisites(4587521, "Sber", 689752, 87896331, 584789, 123012));
        Account account2 = new Account(200000, new Requisites(4587986, "Sber", 689752, 87896784, 584789, 123012));
        Account account3 = new Account(340000, new Requisites(4587127, "Sber", 689752, 87896123, 584789, 123012));
        BankMap list = new BankMap();
        list.addUser(user1);
        list.addUser(user2);
        list.addUser(user3);
        list.addAccountToUser(user1, account1);
        list.addAccountToUser(user2, account2);
        list.addAccountToUser(user3, account3);
        list.deleteUser(user2);
        list.getUserAccount(user2);
    }

    /**
     * Test when transfer money from one user to second user then true
     */
    @Test
    public void whenTransferMoneyFromOneUserToSecondUserThenTrue() {
        Passport passport1 = new Passport(6518, 248478, "USSR", "12.02.1978");
        User user1 = new User("Ivan", passport1);
        Passport passport2 = new Passport(6510, 235469, "USSR", "18.06.1979");
        User user2 = new User("Valerii", passport2);
        Account account1 = new Account(500000, new Requisites(4587521, "Sber", 689752, 87896331, 584789, 123012));
        Account account2 = new Account(200000, new Requisites(4587986, "Sber", 689752, 87896784, 584789, 123012));
        BankMap list = new BankMap();
        list.addUser(user1);
        list.addUser(user2);
        list.addAccountToUser(user1, account1);
        list.addAccountToUser(user2, account2);
        assertThat(list.transferMoney(user1, account1, user2, account2, 100000), is(true));
    }

    /**
     * Test when transfer money from user to user then true.
     */
    @Test
    public void whenTransferMoneyFromUserToUserThenTrue() {
        Passport passport1 = new Passport(6518, 248478, "USSR", "12.02.1978");
        User user1 = new User("Ivan", passport1);
        Passport passport2 = new Passport(6510, 235469, "USSR", "18.06.1979");
        User user2 = new User("Valerii", passport2);
        Account account1 = new Account(500000, new Requisites(4587521, "Sber", 689752, 87896331, 584789, 123012));
        Account account2 = new Account(200000, new Requisites(4587986, "Sber", 689752, 87896784, 584789, 123012));
        BankMap list = new BankMap();
        list.addUser(user1);
        list.addUser(user2);
        list.addAccountToUser(user1, account1);
        list.addAccountToUser(user1, account2);
        list.addAccountToUser(user2, account2);
        assertThat(list.transferMoney(user1, account1, user1, account2, 100000), is(true));
    }

    /**
     * Test when transfer money and haven't enough money then false.
     */
    @Test
    public void whenTransferMoneyAndHaventEnoughMoneyThenFalse() {
        Passport passport1 = new Passport(6518, 248478, "USSR", "12.02.1978");
        User user1 = new User("Ivan", passport1);
        Passport passport2 = new Passport(6510, 235469, "USSR", "18.06.1979");
        User user2 = new User("Valerii", passport2);
        Account account1 = new Account(500000, new Requisites(4587521, "Sber", 689752, 87896331, 584789, 123012));
        Account account2 = new Account(200000, new Requisites(4587986, "Sber", 689752, 87896784, 584789, 123012));
        BankMap list = new BankMap();
        list.addUser(user1);
        list.addUser(user2);
        list.addAccountToUser(user1, account1);
        list.addAccountToUser(user1, account2);
        list.addAccountToUser(user2, account2);
        assertThat(list.transferMoney(user2, account2, user1, account1, 300000), is(false));
    }

    /**
     * Test when transfer money and haven't account then false.
     */
    @Test
    public void whenTransferMoneyAndHaventAccountThenFalse() {
        Passport passport1 = new Passport(6518, 248478, "USSR", "12.02.1978");
        User user1 = new User("Ivan", passport1);
        Passport passport2 = new Passport(6510, 235469, "USSR", "18.06.1979");
        User user2 = new User("Valerii", passport2);
        Account account1 = new Account(500000, new Requisites(4587521, "Sber", 689752, 87896331, 584789, 123012));
        Account account2 = new Account(200000, new Requisites(4587986, "Sber", 689752, 87896784, 584789, 123012));
        BankMap list = new BankMap();
        list.addUser(user1);
        list.addUser(user2);
        list.addAccountToUser(user1, account1);
        list.addAccountToUser(user2, account2);
        assertThat(list.transferMoney(user2, account1, user1, account1, 300000), is(false));
    }

    /**
     * Test when transfer money and haven't user then exception.
     */
    @Test(expected = NullPointerException.class)
    public void whenTransferMoneyAndHaventUserThenException() {
        Passport passport1 = new Passport(6518, 248478, "USSR", "12.02.1978");
        User user1 = new User("Ivan", passport1);
        Passport passport2 = new Passport(6510, 235469, "USSR", "18.06.1979");
        User user2 = new User("Valerii", passport2);
        Account account1 = new Account(500000, new Requisites(4587521, "Sber", 689752, 87896331, 584789, 123012));
        Account account2 = new Account(200000, new Requisites(4587986, "Sber", 689752, 87896784, 584789, 123012));
        BankMap list = new BankMap();
        list.addUser(user1);
        list.addAccountToUser(user1, account1);
        list.addAccountToUser(user1, account2);
        list.transferMoney(user1, account1, user2, account1, 300000);
    }

}
