package ru.job4j.test;

/**
 *  Test task of chapter_001.
 *
 *  @author apermyakov
 *  @since 12.10.2017
 *  @version 1.0
 */
public class TestTask {

    /**
     * Convert strings to chars and equal result chars.
     *
     * @param origin Base word
     * @param sub Check word
     * @return boolean result
     * @since 12.10.2017
     */
    public boolean contains(String origin, String sub) {
        char[] originChar = origin.toCharArray();
        char[] subChar = sub.toCharArray();
        boolean result = false;

        for (int out = 0; out < originChar.length - subChar.length + 1; out++) {
            int equals = 0;
            for (int in = 0; in < subChar.length; in++) {
                if (originChar[out + in] == subChar[in]) {
                    equals++;
                }
                if (equals == subChar.length) {
                    result = true;
                }
            }
        }
        return result;
    }
}
