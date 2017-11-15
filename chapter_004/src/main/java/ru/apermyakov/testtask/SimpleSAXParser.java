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
        return converter;
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

        if (qName.equals("AddOrder")) {
            converter.convertAdd(new Order(attributes.getValue(0),
                    attributes.getValue(1),
                    Double.valueOf(attributes.getValue(2)),
                    Integer.valueOf(attributes.getValue(3)),
                    Integer.valueOf(attributes.getValue(4))));
        }

        if (qName.equals("DeleteOrder")) {
            converter.convertDelete(new Order(attributes.getValue(0),
                    "",
                    0D,
                    0,
                    Integer.valueOf(attributes.getValue(1))));
        }
    }
}
