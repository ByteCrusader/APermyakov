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
        converter.buy.values().forEach(i -> System.out.println(String.format("%s@%s", i.getVolume(), i.getPrice())));
        converter.sell.values().forEach(i -> System.out.println(String.format("%s@%s", i.getVolume(), i.getPrice())));
    }
}
