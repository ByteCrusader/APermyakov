package ru.apermyakov.testtask;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.File;
import java.io.IOException;

/**
 * Class for test SAX parsing orders.
 *
 * @author apermyakov
 * @version 1.0
 * @since 15.11.2017
 */
public class SimpleSAXParserTest {

    /**
     * Test when read from file by SAX parser then build new book and all buy prices lower then all sell prices.
     *
     * @throws ParserConfigurationException wrong configuration
     * @throws SAXException SAX exception
     * @throws IOException file not found
     */
    @Test
    public void whenReadFromFileBySAXParserThenBuildNewBookAndAllBuyPricesLowerThenAllSellPrices() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SimpleSAXParser simpleSAXParser = new SimpleSAXParser();

        String string = String.format(
                "%sorders.xml",
                System.getProperty("java.io.tmpdir")
        );

        parser.parse(new File(string), simpleSAXParser);

        Shower shower = new Shower();
        shower.show(simpleSAXParser.getConverter());
    }

}