package ru.apermyakov.testtask;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test order book.
 *
 * @author apermyakov
 * @version 1.1
 * @since 13.11.2017
 */
public class OrderBookTest {

    /**
     * Test when add new text then build new order.
     */
    @Test
    public void whenAddNewTextThenBuildNewOrder() {
        String textAddOrder = "<AddOrder book=\"book-1\" operation=\"SELL\" price=\"100.50\" volume=\"81\" orderId=\"1\" />";
        Parser addParser = new Parser();
        Order addOrder = addParser.parseAddOrder(textAddOrder);
        assertThat(addOrder.book, is("book-1"));
        assertThat(addOrder.operation, is("SELL"));
        assertThat(addOrder.price, is(100.50));
        assertThat(addOrder.volume, is(81));
        assertThat(addOrder.orderId, is(1));

        String textDelOrder = "<DeleteOrder book=\"book-2\" orderId=\"104\" />";
        Parser delParser = new Parser();
        Order delOrder = delParser.parseDeleteOrder(textDelOrder);
        assertThat(delOrder.book, is("book-2"));
        assertThat(delOrder.operation, is(""));
        assertThat(delOrder.price, is(0D));
        assertThat(delOrder.volume, is(0));
        assertThat(delOrder.orderId, is(104));
    }

    /**
     * Test when add some order then build new book.
     */
    @Test
    public void whenAddSomeOrderThenBuildNewBook() {
        Saver saver = new Saver();
        Parser addParser = new Parser();
        String textAddOrderOne = "<AddOrder book=\"book-1\" operation=\"SELL\" price=\"100.50\" volume=\"81\" orderId=\"1\" />";
        Order addOrderOne = addParser.parseAddOrder(textAddOrderOne);
        saver.addOrder(textAddOrderOne);
        assertThat(saver.converter.sell.get(100.50).book, is(addOrderOne.book));
        String textAddOrderTwo = "<AddOrder book=\"book-2\" operation=\"SELL\" price=\"100.30\" volume=\"75\" orderId=\"104\" />";
        Order addOrderTwo = addParser.parseAddOrder(textAddOrderTwo);
        saver.addOrder(textAddOrderTwo);
        assertThat(saver.converter.sell.get(100.30).book, is(addOrderTwo.book));
        String textDelOrder = "<DeleteOrder book=\"book-2\" orderId=\"104\" />";
        saver.addOrder(textDelOrder);
        assertThat(saver.converter.sell.containsKey(100.30), is(false));
    }

    /**
     * Test when convert book then build sell and buy book.
     */
    @Test
    public void whenConvertBookThenBuildSellAndBuyBook() {
        Saver saver = new Saver();
        String textAddOrderOne = "<AddOrder book=\"book-1\" operation=\"SELL\" price=\"100.50\" volume=\"81\" orderId=\"1\" />";
        saver.addOrder(textAddOrderOne);
        String textAddOrderTwo = "<AddOrder book=\"book-2\" operation=\"SELL\" price=\"100.30\" volume=\"75\" orderId=\"104\" />";
        saver.addOrder(textAddOrderTwo);
        String textDelOrder = "<DeleteOrder book=\"book-2\" orderId=\"104\" />";
        saver.addOrder(textDelOrder);
        assertThat(saver.converter.sell.get(100.50).book, is("book-1"));
        Shower shower = new Shower();
        saver.addOrder("<AddOrder book=\"book-1\" operation=\"SELL\" price=\"100.50\" volume=\"81\" orderId=\"1\" />");
        saver.addOrder("<AddOrder book=\"book-3\" operation=\"BUY\" price=\" 99.50\" volume=\"86\" orderId=\"2\" />");
        saver.addOrder("<AddOrder book=\"book-1\" operation=\"BUY\" price=\" 99.70\" volume=\"16\" orderId=\"3\" />");
        saver.addOrder("<AddOrder book=\"book-3\" operation=\"SELL\" price=\"100.00\" volume=\"80\" orderId=\"4\" />");
        saver.addOrder("<AddOrder book=\"book-2\" operation=\"SELL\" price=\"100.50\" volume=\"79\" orderId=\"5\" />");
        saver.addOrder("<AddOrder book=\"book-2\" operation=\"BUY\" price=\" 99.40\" volume=\"78\" orderId=\"6\" />");
        saver.addOrder("<AddOrder book=\"book-3\" operation=\"SELL\" price=\"100.00\" volume=\"82\" orderId=\"7\" />");
        saver.addOrder("<AddOrder book=\"book-2\" operation=\"SELL\" price=\"100.20\" volume=\"42\" orderId=\"8\" />");
        saver.addOrder("<AddOrder book=\"book-3\" operation=\"SELL\" price=\"100.40\" volume=\"75\" orderId=\"9\" />");
        saver.addOrder("<AddOrder book=\"book-1\" operation=\"BUY\" price=\" 99.80\" volume=\"64\" orderId=\"10\" />");
        saver.addOrder("<AddOrder book=\"book-2\" operation=\"SELL\" price=\"100.50\" volume=\"46\" orderId=\"11\" />");
        saver.addOrder("<AddOrder book=\"book-3\" operation=\"SELL\" price=\"100.00\" volume=\"48\" orderId=\"12\" />");
        saver.addOrder("<AddOrder book=\"book-2\" operation=\"SELL\" price=\"100.20\" volume=\"99\" orderId=\"13\" />");
        saver.addOrder("<AddOrder book=\"book-3\" operation=\"SELL\" price=\"100.40\" volume=\"23\" orderId=\"14\" />");
        shower.show(saver.converter);
    }

    /**
     * Test when read from file then build new book and all buy prices lower then all sell prices.
     */
    @Test
    public void whenReadFromFileThenBuildNewBookAndAllBuyPricesLowerThenAllSellPrices() {
        Saver saver = new Saver();
        try {
            saver.addOrderFromFile("C:\\Users\\APermyakov\\Downloads\\orders.xml");
        } catch (Exception ew) {
            ew.printStackTrace();
        }
        Shower shower = new Shower();
        shower.show(saver.converter);

        for (Order buyOrder : saver.converter.buy.values()) {
            for (Order sellOrder : saver.converter.sell.values()) {
                assertThat(buyOrder.price <= sellOrder.price, is(true));
            }
        }
    }
}