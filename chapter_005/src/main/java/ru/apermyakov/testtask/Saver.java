package ru.apermyakov.testtask;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Class for save orders.
 *
 * @author apermyakov
 * @version 1.1
 * @since 13.11.2017
 */
public class Saver {

    /**
     * Field for initialize converter.
     */
    Converter converter = new Converter();

    /**
     * Method for add orders from file.
     *
     * @param fileWay file
     * @throws FileNotFoundException file not found
     */
    public void addOrderFromFile(String fileWay) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileWay))) {
            String fileString;
            while ((fileString = reader.readLine()) != null) {
                addOrder(fileString);
            }
        } catch (Exception ex) {
            throw new FileNotFoundException();
        }
    }

    /**
     * Method for add orders from string.
     *
     * @param order insert string
     */
    public void addOrder(String order) {
        Parser parser = new Parser();
        if (order.length() > 4 && order.substring(1, 4).equals("Add")) {
            this.converter.convertAdd(parser.parseAddOrder(order));
        } else if (order.length() > 7 && order.substring(1, 7).equals("Delete")) {
            this.converter.convertDelete(parser.parseDeleteOrder(order));
        }
    }
}
