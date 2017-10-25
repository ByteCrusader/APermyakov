package ru.apermyakov.test;

/**
 * Class for initial accounts.
 *
 * @author apermyakov
 * @version 1.0
 * @since 25.10.2017
 */
public class Account {

    /**
     * Field for value of accounts.
     */
    private double value;

    /**
     * Field for requisites of account.
     */
    private Requisites requisites;

    /**
     * Design account.
     *
     * @param value value of accounts - money
     * @param requisites requisites for accounts
     */
    public Account(double value, Requisites requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * Method for get value of account.
     *
     * @return value of account
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Method for set value when deposit and withdrawal.
     *
     * @param value new value
     */
    public void setValue(double value) {
        this.value = value;
    }
}
