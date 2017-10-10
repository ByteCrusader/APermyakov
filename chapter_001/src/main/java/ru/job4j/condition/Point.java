package ru.job4j.condition;

/**
 * Search point on the function.
 *
 * @author apermyakov
 * @version $Id$
 * @since 10.10.2017
 */
public class Point {

    /**
     * Field of Point.
     *
     * @since 10.10.2017
     */
    private int x;

    /**
     * Field of Point.
     *
     * @since 10.10.2017
     */
    private int y;

    /**
     * Create Point.
     *
     * @author apermyakov
     * @param x X
     * @param y Y
     * @since 10.10.2017
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method for return X.
     *
     * @author apermyakov
     * @return X
     * @since 10.10.2017
     */
    public int getX() {
        return this.x;
    }

    /**
     * Method for return Y.
     *
     * @author apermyakov
     * @return Y
     * @since 10.10.2017
     */
    public int getY() {
        return this.y;
    }

    /**
     * Method for search point.
     *
     * @author apermyakov
     * @param a A
     * @param b B
     * @return true or false
     * @since 10.10.2017
     */
    public boolean is(int a, int b) {
        return (getY() == a * getX() + b);
    }
}
