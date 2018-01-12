package ru.apermyakov.testtask.cell;

/**
 * Interface for sell value.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public interface SellValue {

    /**
     * Method for check is sell value set.
     *
     * @return is value set.
     */
    boolean isValueSet();

    /**
     * Method for check sell value is value cross.
     *
     * @return is value cross.
     */
    boolean isCross();
}
