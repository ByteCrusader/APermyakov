package ru.job4j.loop;

/**
 *  Sums of even numbers.
 *
 *  @author apermyakov
 *  @since 11.10.2017
 *  @version 1.0
 */
public class Counter {

    /**
     * Range counter.
     *
     * @param start Range's start
     * @param finish Range's finish
     * @return sum of even
     * @since 11.10.2017
     */
    public int add(int start, int finish) {
        int result = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                result = result + i;
            }
        }
        return result;
    }
}
