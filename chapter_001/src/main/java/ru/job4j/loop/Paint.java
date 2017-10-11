package ru.job4j.loop;

/**
 *  Pyramid.
 *
 *  @author apermyakov
 *  @since 11.10.2017
 *  @version 1.0
 */
public class Paint {
    /**
     * Draw pyramid.
     *
     * @author apermyakov
     * @param h height of pyramid
     * @return pyramid
     * @since 11.10.2017
     */
    public String piramid(int h) {
        String enter = System.getProperty("line.separator");
        StringBuilder string = new StringBuilder();
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= 2 * h - 1; j++) {
                if (j > h - i && j < h + i) {
                    string.append("^");
                } else {
                    string.append(" ");
                }
            }
            if (i < h) {
                string.append(enter);
            }
        }
        return string.toString();
    }
}
