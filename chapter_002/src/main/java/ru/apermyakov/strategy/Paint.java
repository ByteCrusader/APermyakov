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
	* Feild to shape.
	*/
	private Shape shape;

	/**
	* Design paint with shape.
	*
	* @param shape some shape
	* @author apermyakov
	* @since 16.10.2017
	* @version 1.0
	*/
	public Paint(Shape shape) {
		this.shape = shape;
	}

	/**
	* Method for print some shape into console.
	*
	* @since 16.10.2017
	* @version 1.0
	*/
	public void drow() {
		System.out.println(shape.pic());
	}
}