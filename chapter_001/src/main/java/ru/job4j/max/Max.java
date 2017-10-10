package ru.job4j.max;

/**
 * Max from two value.
 *
 * @author apermyakov
 * @version $Id$
 * @since 10.10.2017
 */
public class Max {

    /**
     * Method for search max of two value.
     *
     * @author apermyakov
     * @param first first value
     * @param second second value
     * @return result
     * @since 10.10.2017
     */
    public int max(int first, int second) {
        return (first > second ? first : second);
    }
}
