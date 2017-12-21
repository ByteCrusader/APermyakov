package ru.apermyakov.testtask;

/**
 * Class for show result.
 *
 * @author apermyakov
 * @version 1.0
 * @since 13.11.2017
 */
public class Shower {

    /**
     * Method for show result maps.
     *
     * @param converter converter
     */
    public void show(Converter converter) {
        for (Order order : converter.buy.values()) {
            System.out.println(String.format("%s@%s", order.volume, order.price));
        }
        for (Order order : converter.sell.values()) {
            System.out.println(String.format("%s@%s", order.volume, order.price));
        }
    }
}
