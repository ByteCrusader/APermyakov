package ru.apermyakov.testtask.cell;

/**
 * Class for modulate board sell.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public class BoardSell implements Sell {

    /**
     * Field for set value flag.
     */
    private boolean setValue = false;

    /**
     * Field for sell value.
     */
    private boolean cross;

    /**
     * Field for sell height.
     */
    private int height;

    /**
     * Field for sell width.
     */
    private int width;

    /**
     * Board sell constructor.
     *
     * @param height sell height.
     * @param width sell width.
     */
    public BoardSell(int height, int width) {
        this.height = height;
        this.width = width;
    }

    /**
     * Method for set cross to sell.
     */
    @Override
    public void setValueCross() {
        this.setValue = true;
        this.cross = true;
    }

    /**
     * Method for get sell height.
     *
     * @return sell height.
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * Method for check is sell value set.
     *
     * @return is value set.
     */
    @Override
    public boolean isValueSet() {
        return this.setValue;
    }

    /**
     * Method for check sell value is value cross.
     *
     * @return is value cross.
     */
    @Override
    public boolean isCross() {
        return this.cross;
    }

    /**
     * Method for get sell width.
     *
     * @return sell width.
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * Method for set sell value zero.
     */
    @Override
    public void setValueZero() {
        this.setValue = true;
        this.cross = false;
    }

    /**
     * Method for check is this sell coincidence for coordinates.
     *
     * @param height height coordinate.
     * @param width width coordinate.
     * @return is coincidence.
     */
    @Override
    public boolean isCoincidence(int height, int width) {
        return height == this.height && width == this.width;
    }
}
