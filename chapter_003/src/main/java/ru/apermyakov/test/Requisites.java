package ru.apermyakov.test;

/**
 * Class for initial bank map of users.
 *
 * @author apermyakov
 * @version 1.0
 * @since 25.10.2017
 */
public class Requisites {

    /**
     * Field of account number.
     */
    private long accountNumber;

    /**
     * Field of name of bank.
     */
    private String nameOfBank;

    /**
     * Field of BIC.
     */
    private int bIC;

    /**
     * Field of corporate account.
     */
    private long corporateAccount;

    /**
     * Field of TIN.
     */
    private int tIN;

    /**
     * Field of CPR.
     */
    private int cPR;

    /**
     * Design requisites of account.
     *
     * @param accountNumber account number
     * @param nameOfBank name of bank
     * @param bIC BIC
     * @param corporateAccount corporate account
     * @param tIN TIN
     * @param cPR CPR
     */
    public Requisites(long accountNumber, String nameOfBank, int bIC, long corporateAccount, int tIN, int cPR) {
        this.accountNumber = accountNumber;
        this.nameOfBank = nameOfBank;
        this.bIC = bIC;
        this.corporateAccount = corporateAccount;
        this.tIN = tIN;
        this.cPR = cPR;
    }
}
