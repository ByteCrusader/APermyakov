package ru.job4j.calculator;

/**
 * Simple calculator.
 *
 * @author apermyakov
 * @version $Id$
 * @since 10.10.2017
 * @version 1.1
 */
public class Calculator {

    /**
     * Field.
     *
     * @since 10.10.2017
     */
    private double result;

    /**
     * Add method.
     *
     * @author apermyakov
     * @param first first value
     * @param second second value
     * @since 10.10.2017
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Subtract method.
     *
     * @author apermyakov
     * @param first first value
     * @param second second value
     * @since 10.10.2017
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Div method.
     *
     * @author apermyakov
     * @param first first value
     * @param second second value
     * @since 10.10.2017
     */
    public void div(double first, double second) {
        try {
            this.result = first / second;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Multiple method.
     *
     * @author apermyakov
     * @param first first value
     * @param second second value
     * @since 10.10.2017
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * Method for return result.
     *
     * @author apermyakov
     * @return result
     * @since 10.10.2017
     */
    public double getResult() {
        return this.result;
    }
}
