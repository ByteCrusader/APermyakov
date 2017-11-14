package ru.apermyakov.testtask;

/**
 * Class for parsing string to order.
 *
 * @author apermyakov
 * @version 1.0
 * @since 13.11.2017
 */
public class Parser {

    /**
     * Method for parsing string to string array.
     *
     * @param text insert test
     * @return string array
     */
    private String[] parse(String text) {
        String[] parsingText = new String[5];
        int parsingIndex = 0;
        int startIndex = -1;
        boolean startRecord = false;

        for (int index = 0; index < text.length(); index++) {
            if (text.charAt(index) == '\"') {
                if (startRecord) {
                    parsingText[parsingIndex++] = text.substring(startIndex, index);
                    startRecord = false;
                } else {
                    startRecord = true;
                }
                startIndex = index + 1;
            }
        }

        return parsingText;
    }

    /**
     * Method for parsing add orders string.
     *
     * @param text insert string
     * @return add order
     */
    public Order parseAddOrder(String text) {
        String[] addOrder = this.parse(text);
        return new Order(addOrder[0], addOrder[1], Double.valueOf(addOrder[2]), Integer.valueOf(addOrder[3]), Integer.valueOf(addOrder[4]));
    }

    /**
     * Method for parsing delete order string.
     *
     * @param text insert text
     * @return delete order
     */
    public Order parseDeleteOrder(String text) {
        String[] deleteOrder = this.parse(text);
        return new Order(deleteOrder[0], "", 0D, 0, Integer.valueOf(deleteOrder[1]));
    }
}
