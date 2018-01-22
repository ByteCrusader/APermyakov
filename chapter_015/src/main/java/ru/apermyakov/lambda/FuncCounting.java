package ru.apermyakov.lambda;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class for function counting.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 22.01.2018.
 */
public class FuncCounting {

    /**
     * Method for counting diapason.
     *
     * @param start start element.
     * @param end end element.
     * @param function function.
     * @return list of diapason.
     */
    List<Double> diapason(int start, int end, Function<Integer, Double> function) {
        return IntStream
                .range(start, end)
                .boxed()
                .map(function)
                .collect(Collectors.toList());
    }
}
