package ru.apermyakov;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for parse xml file.
 *
 * @author apermyakov
 * @version 1.0
 * @since 30.11.2017
 */
public class TrackerSAXParser extends DefaultHandler {

    /**
     * Field for result's container.
     */
    private Map<String, String> result = new HashMap<>();

    /**
     * Method for get results.
     *
     * @return results
     */
    public Map<String, String> getResult() {
        return result;
    }

    /**
     * Field for save qName.
     */
    private String key;

    /**
     * Field for record data to map.
     */
    private boolean record = false;

    /**
     * Override method for add key to map.
     *
     * @param uri uri
     * @param localName local name
     * @param qName q name
     * @param attributes attributes
     * @throws SAXException sax e
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.endsWith("script")) {
            result.put(qName, "");
            key = qName;
            record = true;
        }
    }

    /**
     * Override method for add value to map.
     *
     * @param ch ch
     * @param start start
     * @param length length
     * @throws SAXException sax e
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (record) {
            result.computeIfPresent(key, (k, v) -> v = new String(ch, start, length));
            record = false;
        }
    }
}
