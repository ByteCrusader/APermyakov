package ru.apermyakov.testtask;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

/**
 * Class for parsing orders by SAX parser.
 *
 * @author apermyakov
 * @version 1.0
 * @since 15.11.2017
 */
public class SimpleSAXParser extends DefaultHandler {

    /**
     * Field for container.
     */
    private Converter converter = new Converter();

    /**
     * Method for get container.
     *
     * @return return converter
     */
    public Converter getConverter() {
        return this.converter;
    }

    /**
     * Method for parsing xml string.
     *
     * @param uri uri
     * @param localName local name
     * @param qName needed name
     * @param attributes needed attributes
     * @throws SAXException SAX exception
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (("AddOrder").equals(qName)) {
            this.converter.convertAdd(new Order(attributes.getValue("book"),
                    attributes.getValue("operation"),
                    Double.valueOf(attributes.getValue("price")),
                    Integer.valueOf(attributes.getValue("volume")),
                    Integer.valueOf(attributes.getValue("orderId"))));
        }

        if (("DeleteOrder").equals(qName)) {
            this.converter.convertDelete(new Order(attributes.getValue("book"),
                    "",
                    0D,
                    0,
                    Integer.valueOf(attributes.getValue("orderId"))));
        }
    }
}
