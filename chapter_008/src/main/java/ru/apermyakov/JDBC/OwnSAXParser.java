package ru.apermyakov.JDBC;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.List;

/**
 * Class for parse xml file.
 *
 * @author apermyakov
 * @version 1.0
 * @since 28.11.2017
 */
public class OwnSAXParser extends DefaultHandler {

    /**
     * Field for result's container.
     */
    private List<Integer> result = new LinkedList<>();

    /**
     * Method for get results.
     *
     * @return results
     */
    public List<Integer> getResult() {
        return result;
    }

    /**
     * Override start element to parse xml.
     *
     * @param uri uri
     * @param localName local name
     * @param qName q name
     * @param attributes attributes
     * @throws SAXException sax e
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (("entry").equals(qName)) {
            String field = attributes.getValue("field");
            result.add(Integer.valueOf(field.substring(0, field.indexOf(" "))));
        }
    }
}
