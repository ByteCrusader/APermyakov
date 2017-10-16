package ru.apermyakov.strategy;

/**
* Class for paint anyone shape.
*
* @author apermyakov
* @since 16.10.2017
* @version 1.0
*/
public class Paint {

	/**
	* Method for print some shape into console.
	*
	* @param shape some shape
	* @since 16.10.2017
	* @version 1.0
	*/
	public void drow(Shape shape) {
		System.out.println(shape.pic());
	}
}