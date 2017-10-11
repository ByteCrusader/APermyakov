package ru.job4j.loop;

/**
 * Factorial calculation.
 *
 * @author apermyakov
 * @since 11.10.2017
 * @version 1.0
 */
public class Factorial {

    /**
     * Calculation factorial of value n.
     *
     * @param n calc value
     * @return factorial
     * @since 11.10.2017
     */
    public int calc(int n) {
        int result = 1;
        if (n != 0) {
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
        }
        return result;
    }
}
