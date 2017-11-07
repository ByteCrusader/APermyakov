package ru.apermyakov.collections;

/**
 *Class for sort actions.
 *
 *@author apermyakov
 *@version $Id$
 *@since 26.10.2017
 */
public class Comparing {

    /**
     * Field for object one.
     */
    private String objectOne;

    /**
     * Field for object two.
     */
    private String objectTwo;

    /**
     * Field for parsing object one.
     */
    private String[] splitObjectOne;

    /**
     * Field for parsing object two.
     */
    private String[] splitObjectTwo;

    /**
     * Design comparing.
     *
     * @param o1 object one
     * @param o2 object two
     */
    public Comparing(String o1, String o2) {
        this.objectOne = o1;
        this.objectTwo = o2;
        this.splitObjectOne = o1.split("\\\\");
        this.splitObjectTwo = o2.split("\\\\");
    }

    /**
     * Method for calculate difference of the objects length.
     *
     * @return difference of the length
     */
    private int lengthDif() {
        return Integer.compare(this.objectOne.length(), this.objectTwo.length());
    }

    /**
     * Method for calculate total objects length.
     *
     * @return total length
     */
    private int totalLength() {
        return this.splitObjectOne.length + this.splitObjectTwo.length;
    }

    /**
     * Method for ascending compare items with one index.
     *
     * @param index index of items
     * @return ascending result
     */
    private int compareItems(int index) {
        return this.splitObjectOne[index].compareTo(this.splitObjectTwo[index]);
    }

    /**
     * Method for ascending compare last items of objects.
     *
     * @return ascending return
     */
    private int compareLastItems() {
        return this.splitObjectOne[this.splitObjectOne.length - 1].compareTo(this.splitObjectTwo[this.splitObjectTwo.length - 1]);
    }

    /**
     * Method for decreasing compare items with one index.
     *
     * @param index index of items
     * @return decreasing result
     */
    private int decreasCompareItems(int index) {
        return this.splitObjectOne[index].compareTo(this.splitObjectTwo[index]) == 1 ? -1
                : this.splitObjectOne[index].compareTo(this.splitObjectTwo[index]) == -1 ? 1
                : 0;
    }

    /**
     * Method for decreasing compare last items of objects.
     *
     * @return decreasing result
     */
    private int decreasCompareLastItems() {
        return this.splitObjectOne[this.splitObjectOne.length - 1].compareTo(this.splitObjectTwo[this.splitObjectTwo.length - 1]) == 1 ? -1
                : this.splitObjectOne[this.splitObjectOne.length - 1].compareTo(this.splitObjectTwo[this.splitObjectTwo.length - 1]) == -1 ? 1
                : 0;
    }

    /**
     * Method for ascending check of departments.
     *
     * @return ascending result
     */
    public int checkDepartment() {
        return this.compareItems(0) == 0 ? this.checkService() : this.compareItems(0);
    }

    /**
     * Method for ascending check of services under departments.
     *
     * @return ascending result
     */
    private int checkService() {
        return this.totalLength() > 4 ? this.checkGroup()
                : this.lengthDif() == 0 ? this.compareLastItems()
                : this.lengthDif();
    }

    /**
     * Method for ascending check of groups under services.
     *
     * @return ascending result
     */
    private int checkGroup() {
        return this.lengthDif() == 0 ? this.compareLastItems()
                : this.compareItems(1) != 0 ? this.compareItems(1)
                : this.lengthDif();
    }

    /**
     * Method for decreasing check of departments.
     *
     * @return decreasing result
     */
    public int decreasingCheckDepartment() {
        return this.decreasCompareItems(0) == 0 ? this.decreasingCheckService() : this.decreasCompareItems(0);
    }

    /**
     * Method for decreasing check of services under departments.
     *
     * @return decreasing result
     */
    private int decreasingCheckService() {
        return this.totalLength() > 4 ? this.decreasingCheckGroup()
                : this.lengthDif() == 0 ? this.decreasCompareLastItems()
                : this.lengthDif();
    }

    /**
     * Method for decreasing check of groups under services.
     *
     * @return decreasing result
     */
    private int decreasingCheckGroup() {
        return this.lengthDif() == 0 ? this.decreasCompareLastItems()
                : this.decreasCompareItems(1) != 0 ? this.decreasCompareItems(1)
                : this.lengthDif();
    }
}
