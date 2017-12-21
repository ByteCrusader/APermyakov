package ru.apermyakov.testtask;

/**
 * Class for modulate order for book.
 *
 * @author apermyakov
 * @version 1.0
 * @since 13.11.2017
 */
public class Order {

    /**
     * Field for name of book.
     */
    String book;

    /**
     * Field for name of operation.
     */
    String operation;

    /**
     * Field for order price.
     */
    double price;

    /**
     * Field for order volume.
     */
    int volume;

    /**
     * Field for order ID.
     */
    int orderId;

    /**
     * Design new order.
     *
     * @param book name of book
     * @param operation name of operation
     * @param price order price
     * @param volume order volume
     * @param orderId order ID
     */
    public Order(String book, String operation, double price, int volume, int orderId) {
        this.book = book;
        this.operation = operation;
        this.price = price;
        this.volume = volume;
        this.orderId = orderId;
    }
}
