package ru.apermyakov.test;

/**
 * Class for initial bank map of users.
 *
 * @author apermyakov
 * @version 1.0
 * @since 25.10.2017
 */
public class Passport {

    /**
     * Field for passport series.
     */
    private int passportSeries;

    /**
     * Field for passport id.
     */
    private int passportID;

    /**
     * Field for issued by.
     */
    private String issuedBy;

    /**
     * Field for date of issue.
     */
    private String dateOfIssue;

    /**
     * Design passport of user.
     *
     * @param passportSeries passport series
     * @param passportID passport id
     * @param issuedBy issued by
     * @param dateOfIssue date of issue
     */
    public Passport(int passportSeries, int passportID, String issuedBy, String dateOfIssue) {
        this.passportSeries = passportSeries;
        this.passportID = passportID;
        this.issuedBy = issuedBy;
        this.dateOfIssue = dateOfIssue;
    }
}
