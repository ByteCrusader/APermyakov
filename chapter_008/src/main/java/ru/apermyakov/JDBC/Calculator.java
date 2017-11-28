package ru.apermyakov.JDBC;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

/**
 * Class for calculate result of data base bean.
 *
 * @author apermyakov
 * @version 1.0
 * @since 28.11.2017
 */
public class Calculator {

    /**
     * Method for parsing 2.xml file.
     *
     * @return list of parsing integers
     */
    private List<Integer> parseXMLFile() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        OwnSAXParser ownSAXParser = new OwnSAXParser();
        try {
            parser.parse("2.xml", ownSAXParser);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ownSAXParser.getResult();
    }

    /**
     * Method for calculate result of parsing.
     *
     * @param resultOfParsing result of parsing
     * @return sum of integers
     */
    private int calculate(List<Integer> resultOfParsing) {
        int sum = 0;
        for (int number : resultOfParsing) {
            sum += number;
        }
        return sum;
    }

    /**
     * Method for show result of calculator.
     */
    public void calculateAndShowResult() {
        System.out.println(calculate(parseXMLFile()));
    }
}
