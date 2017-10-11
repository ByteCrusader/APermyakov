package ru.job4j.loop;

/**
 *  Implement chess board.
 *
 *  @author apermyakov
 *  @since 11.10.2017
 *  @version 1.0
 */
public class Board {

    /**
     * Draw chess board.
     * @param width width
     * @param height height
     * @return chess board string
     * @since 11.10.2017
     */
    public String paint(int width, int height) {
        String enter = System.getProperty("line.separator");
        StringBuilder string = new StringBuilder();
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                if (j % 2 != 0 && i % 2 != 0 || j % 2 == 0 && i % 2 == 0) {
                    string.append("x");
                } else {
                    string.append(" ");
                }
            }
            string.append(enter);
        }
        return string.toString();
    }
}
