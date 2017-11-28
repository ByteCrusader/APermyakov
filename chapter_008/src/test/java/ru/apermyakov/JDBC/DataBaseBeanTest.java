package ru.apermyakov.JDBC;

import org.junit.Test;

/**
 * Class for test data base bean.
 */
public class DataBaseBeanTest {

    /**
     * Test when initial bean then take n.
     */
    @Test
    public void whenInitialBeanThenTakeN() {
        DataBaseBean bean = new DataBaseBean();
        bean.setUrlOfDb("C:\\check\\TEST.sqlite");
        bean.setNumberOfRecords(1000000);
        bean.action();
    }
}