package ru.job4j.condition;

/**
 * Calculation triangle area.
 *
 * @author apermyakov
 * @version $Id$
 * @since 10.10.2017
 */
public class Triangle {
    /**
     * Field point a.
     *
     * @since 10.10.2017
     */
    private Point a;
    /**
     * Field point b.
     *
     * @since 10.10.2017
     */
    private Point b;
    /**
     * Field point c.
     *
     * @since 10.10.2017
     */
    private Point c;

    /**
     * Initialization triangle.
     *
     * @author apermyakov
     * @param a point a
     * @param b point b
     * @param c point c
     * @since 10.10.2017
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Distance between left and right points.
     *
     * @author apermyakov
     * @param left left point
     * @param right right point
     * @return distance between left and right
     * @since 10.10.2017
     */
    public double distance(Point left, Point right) {
        return Math.sqrt(Math.pow((right.getX() - left.getX()), 2D) + Math.pow((right.getY() - left.getY()), 2D));
    }

    /**
     * Period of triangle.
     *
     * @author apermyakov
     * @param ab distance between a b
     * @param ac distance between a c
     * @param bc distance between b c
     * @return period of triangle
     * @since 10.10.2017
     */
    public double period(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2;
    }

    /**
     * Area of triangle.
     *
     * @author apermyakov
     * @return triangle's area
     * @since 10.10.2017
     */
    public double area() {
        double rsl = -1;
        double ab = this.distance(this.a, this.b);
        double ac = this.distance(this.a, this.c);
        double bc = this.distance(this.b, this.c);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }

    /**
     * Exist of triangle.
     *
     * @author apermyakov
     * @param ab distance between a b
     * @param ac distance between a c
     * @param bc distance between b c
     * @return possibility of building triangle
     * @since 10.10.2017
     */
    private boolean exist(double ab, double ac, double bc) {
        return ((ab + ac > bc) && (ab + bc > ac) && (ac + bc > ab));
    }
}
