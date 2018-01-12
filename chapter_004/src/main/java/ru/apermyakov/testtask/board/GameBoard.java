package ru.apermyakov.testtask.board;

import ru.apermyakov.testtask.cell.BoardSell;
import ru.apermyakov.testtask.cell.Sell;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for modulate game board.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public class GameBoard implements Board {

    /**
     * Field for board height.
     */
    private int height;

    /**
     * Field for board width.
     */
    private int width;

    /**
     * Field for contain board sells.
     */
    private List<Sell> sells = new ArrayList<>();

    /**
     * Constructor for game board.
     *
     * @param height board height.
     * @param width board width.
     */
    public GameBoard(int height, int width) {
        this.height = height;
        this.width = width;
    }

    /**
     * Method for set value to board sell by coordinates.
     *
     * @param height height coordinate.
     * @param width width coordinate.
     * @param isCross value.
     */
    @Override
    public void setValue(int height, int width, boolean isCross) {
        Sell foundSell = this.selectSell(height, width);
        if (isCross) {
            foundSell.setValueCross();
        } else {
            foundSell.setValueZero();
        }
    }

    /**
     * Method for initial board.
     */
    @Override
    public void initialBoard() {
        for (int outsideIndex = 1; outsideIndex <= this.height; outsideIndex++) {
            for (int insideIndex = 1; insideIndex <= this.width; insideIndex++) {
                this.sells.add(new BoardSell(outsideIndex, insideIndex));
            }
        }
    }

    /**
     * Method for select sell.
     *
     * @param height height coordinate.
     * @param width width coordinate.
     * @return found sell.
     */
    @Override
    public Sell selectSell(int height, int width) {
        Sell result = new BoardSell(-1, -1);
        for (Sell sell : this.sells) {
            if (sell.isCoincidence(height, width)) {
                result = sell;
                break;
            }
        }
        return result;
    }

    /**
     * Method for build board line separator.
     *
     * @param width board width.
     * @return line separator.
     */
    protected String buildBoardLine(int width) {
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < width; index++) {
            builder.append(index == width - 1 ? "-" : "--");
        }
        builder.append(System.lineSeparator());
        return builder.toString();
    }

    /**
     * Generate method for build board vertical separators.
     *
     * @param index sell index.
     * @param firstSep first separator.
     * @param secondSep second separator.
     * @return result separator.
     */
    protected String buildBoardSeparators(int index, String firstSep, String secondSep) {
        return index == this.height ? firstSep : secondSep;
    }

    /**
     * Method for build board sell value.
     *
     * @param sell sell.
     * @return value.
     */
    protected String buildBoardSell(Sell sell) {
        return sell.isValueSet()
                ? sell.isCross() ? "X" : "O"
                : " ";
    }

    /**
     * Method for get board.
     *
     * @return result board.
     */
    @Override
    public String getBoard() {
        StringBuilder builder = new StringBuilder();
        int index = 0;
        for (int outsideIndex = 1; outsideIndex <= this.height; outsideIndex++) {
            for (int insideIndex = 1; insideIndex <= this.width; insideIndex++) {
                Sell sell = this.sells.get(index);
                if (insideIndex % 2 == 0) {
                    builder.append("|");
                    builder.append(buildBoardSell(sell));
                    builder.append(buildBoardSeparators(insideIndex, System.lineSeparator(), "|"));
                } else {
                    builder.append(buildBoardSell(sell));
                    builder.append(buildBoardSeparators(insideIndex, System.lineSeparator(), ""));
                }
                index++;
            }
            builder.append(buildBoardSeparators(outsideIndex, "", buildBoardLine(this.width)));
        }
        return builder.toString();
    }

    /**
     * Method for get sells.
     *
     * @return list of sells.
     */
    @Override
    public List<Sell> getSells() {
        return this.sells;
    }
}
