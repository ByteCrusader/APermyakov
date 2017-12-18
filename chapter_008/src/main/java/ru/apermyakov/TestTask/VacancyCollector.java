package ru.apermyakov.TestTask;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class for build vacancy collector.
 *
 * @author apermyakov
 * @version 1.0
 * @since 05.12.2017
 */
public class VacancyCollector extends TimerTask{

    /**
     * Field for log4j logger.
     */
    private static final Logger logger = Logger.getLogger(VacancyCollector.class);

    /**
     * Field for contain last data of vacancy.
     */
    private String lastData;

    /**
     * Field for main database worker.
     */
    private DatabaseWorker worker;

    /**
     * Field for contain current year.
     */
    private String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(2);

    /**
     * Field for act script map.
     */
    private Map<String, String> scripts = new HashMap<>();

    /**
     * Method for add vacancy to data base.
     *
     * @param name name of vacancy
     * @param href href of vacancy
     */
    private void addToDb(String name, String href) {
        this.worker.mainActivity("add", name, href, this.lastData);
    }

    /**
     * Method for check vacancy's name.
     *
     * @param element element
     * @return suitable or not
     */
    private boolean checkVacancyName(Element element) {
        return element.hasClass("postslisttopic")
                && element.getElementsByTag("a").first().ownText().toLowerCase().contains("java")
                && !element.getElementsByTag("a").first().ownText().toLowerCase().contains("javascript");
    }

    /**
     * Method for check vacancy's data.
     *
     * @param element element
     * @param record suitable vacancy by name or not
     * @return suitable or not
     */
    private boolean checkVacancyData(Element element, boolean record) {
        return record
                && element.hasClass("altCol")
                && !element.ownText().isEmpty();
    }

    /**
     * Method for check vacancy's data for relevance.
     *
     * @return relevance or not
     */
    private boolean currentYearCheck() {
        return this.lastData.contains(this.currentYear + ",")
                || this.lastData.contains("сегодня")
                || this.lastData.contains("вчера");
    }

    /**
     * Method for parse HTML page.
     *
     * @param url url of page
     */
    private void parseHTML(String url) {
        try {
            Document resultDocument = Jsoup.connect(url).get();
            boolean record = false;
            String name = "";
            String href = "";

            for (Element element : resultDocument.getElementsByTag("tr")) {
                for (Element insideElement : element.children()) {
                    if (checkVacancyName(insideElement)) {
                        Element vacancy = insideElement.getElementsByTag("a").first();
                        name = vacancy.ownText();
                        href = vacancy.attributes().get("href");
                        record = true;
                    }

                    if (checkVacancyData(insideElement, record)) {
                        this.lastData = insideElement.ownText();
                        if (currentYearCheck()) {
                            addToDb(name, href);
                        }
                        record = false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for initial first start of app.
     */
    private void firstStart() {
        int index = this.scripts.get("url").lastIndexOf("/") + 1;
        int page = Integer.valueOf(this.scripts.get("url").substring(index));
        this.parseHTML(this.scripts.get("url"));
        while (this.lastData.contains(this.currentYear + ",")) {
            this.parseHTML(this.scripts.get("url").substring(0, index) + (++page));
        }
        logger.log(Level.INFO, "First start done");
    }

    /**
     * Method for record vacancy to data base.
     */
    private void recordByPeriod() {
        this.worker.mainActivity("initial");
        if (this.worker.mainActivity("check")) {
            this.parseHTML(this.scripts.get("url"));
            logger.log(Level.INFO, "Update done");
        } else {
            this.firstStart();
        }
        this.worker.mainActivity("end");
    }

    /**
     * Method for parse xml file with sql scripts.
     *
     * @return map of scripts in xml file
     */
    private Map<String, String> parseXMLFile() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        TestTaskSAXParser ownSAXParser = new TestTaskSAXParser();
        parser.parse(this.getClass().getClassLoader().getResourceAsStream("Scripts.xml"), ownSAXParser);
        return ownSAXParser.getResult();
    }

    /**
     * Method for initial record vacancy to data base by periods.
     */
    public void record() {
        BasicConfigurator.configure();
        try {
            this.scripts.putAll(parseXMLFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.worker = new DatabaseWorker(this.scripts);
        Timer timer = new Timer();
        timer.schedule(this, 0, Long.valueOf(this.scripts.get("schedule")));
    }

    /**
     * Method for show results of collection.
     */
    public void showResults() {
        this.worker.mainActivity("result");
    }

    /**
     * Method for override run to build background update thread.
     */
    @Override
    public void run() {
        recordByPeriod();
    }

    /**
     * Main method for check correct execution.
     *
     * @param args args
     */
    public static void main(String[] args) {
        VacancyCollector parser = new VacancyCollector();
        parser.record();
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        parser.showResults();
    }
}