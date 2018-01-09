package ru.job4j.calculator;

/**
 * Class for contain result of calculating.
 *
 * @author apermyakov
 * @version 1.0
 * @since 09.01.2018
 */
public class Result {

    /**
     * Field for contain last result.
     */
    private double lastResult;

    /**
     * Method for get last result.
     *
     * @return last result.
     */
    public double getLastResult() {
        return lastResult;
    }

    /**
     * Method for set last result.
     *
     * @param lastResult last result.
     */
    public void setLastResult(double lastResult) {
        this.lastResult = lastResult;
    }
}
