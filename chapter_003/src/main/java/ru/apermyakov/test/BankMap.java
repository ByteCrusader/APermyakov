package ru.apermyakov.test;

import java.util.*;

/**
 * Class for initial bank map of users.
 *
 * @author apermyakov
 * @version 1.0
 * @since 25.10.2017
 */
public class BankMap {

    /**
     * Field for map of users.
     */
    private Map<User, List<Account>> catalog = new HashMap<>();

    /**
     * Method for add user to bank map.
     *
     * @param user new user
     */
    public void addUser(User user) {
        this.catalog.put(user, null);
    }

    /**
     * Method for delete user out of the bank map.
     *
     * @param user delete user
     * @throws NullPointerException Empty user
     */
    public void deleteUser(User user) throws NullPointerException {
        if (this.catalog.containsKey(user)) {
            this.catalog.remove(user);
        } else {
            throw new NullPointerException("No such user");
        }
    }

    /**
     * Method for add account to user.
     *
     * @param user bank user
     * @param account new account
     */
    public void addAccountToUser(User user, Account account) {
        if (this.catalog.get(user) == null) {
            this.catalog.put(user, new LinkedList<Account>());
        }
        this.catalog.get(user).add(account);
    }

    /**
     * Method for delete account from bank user.
     *
     * @param user bank user
     * @param account delete account
     * @throws NullPointerException user has't such account
     */
    public void deleteAccountFromUser(User user, Account account)  throws NullPointerException {
        if (this.catalog.get(user).contains(account)) {
            this.catalog.get(user).remove(account);
        } else {
            throw new NullPointerException("User has't such account");
        }
    }

    /**
     * Method for get user's accounts.
     *
     * @param user bank user
     * @return user's accounts
     * @throws NullPointerException empty user
     */
    public List<Account> getUserAccount(User user) throws NullPointerException {
        if (this.catalog.containsKey(user)) {
            return this.catalog.get(user);
        } else {
            throw new NullPointerException("No such user");
        }
    }

    /**
     * Method for transfer money from one account to another.
     *
     * @param srcUser source bank user
     * @param srcAccount source user account
     * @param dstUser dispatch bank user
     * @param dstAccount dispatch user account
     * @param amount value of money
     * @return transferred or not
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean result = false;

        AccountActivity source = new AccountActivity(srcUser, srcAccount);
        AccountActivity dispatch = new AccountActivity(dstUser, dstAccount);
        if (source.checkUserAccount() && dispatch.checkUserAccount()) {
            if (source.availableSavings() > amount) {
                source.withdrawal(amount);
                source.deposit(amount);
                result = true;
            }
        }
        return result;
    }

    /**
     * Private class for account activity.
     *
     * @author apermyakov
     * @version 1.0
     * @since 25.10.2017
     */
    private class AccountActivity {

        /**
         * Field of user.
         */
        private User user;

        /**
         * Field of user account.
         */
        private Account account;

        /**
         * Field of account index.
         */
        private int accountIndex;

        /**
         * Design account activity.
         *
         * @param user bank user
         * @param account needed account
         */
        public AccountActivity(User user, Account account) {
            this.user = user;
            this.account = account;
        }

        /**
         * Method for check user and account.
         *
         * @return exist or not
         */
        private boolean checkUserAccount() {
            boolean result = getUserAccount(this.user) != null && getUserAccount(this.user).contains(this.account);
            if (result) {
                this.accountIndex =  getUserAccount(this.user).indexOf(this.account);
            }
            return result;
        }

        /**
         * Method for check available saving.
         *
         * @return value of available saving
         */
        private double availableSavings() {
            return getUserAccount(this.user).get(this.accountIndex).getValue();
        }

        /**
         * Method for withdrawal account on amount money.
         *
         * @param amount amount money
         */
        private void withdrawal(double amount) {
            double balance = availableSavings() - amount;
            getUserAccount(this.user).get(this.accountIndex).setValue(balance);
        }

        /**
         * Method for deposit account on amount money.
         *
         * @param amount amount money
         */
        private void deposit(double amount) {
            double balance = availableSavings() + amount;
            getUserAccount(this.user).get(this.accountIndex).setValue(balance);
        }
    }
}
