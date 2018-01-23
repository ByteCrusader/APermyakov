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
    private String book;

    /**
     * Field for name of operation.
     */
    private String operation;

    /**
     * Field for order price.
     */
    private double price;

    /**
     * Field for order volume.
     */
    private int volume;

    /**
     * Field for order ID.
     */
    private int orderId;

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

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
