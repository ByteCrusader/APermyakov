package ru.apermyakov.strategy;

/**
* Class for paint square shape.
*
* @author apermyakov
* @since 16.10.2017
* @version 1.0
*/
public class Square implements Shape {

	/**
	* Method for build square shape.
	*
	* @author apermyakov
	* @since 16.10.2017
	* @version 1.0
	* @return build square to string
	*/
	public String pic() {
		StringBuilder picture = new StringBuilder();
		picture.append("++++++");
		picture.append(System.getProperty("line.separator"));
		picture.append("+xxxx+");
		picture.append(System.getProperty("line.separator"));
		picture.append("+xxxx+");
		picture.append(System.getProperty("line.separator"));
		picture.append("+xxxx+");
		picture.append(System.getProperty("line.separator"));
		picture.append("+xxxx+");
		picture.append(System.getProperty("line.separator"));
		picture.append("++++++");
		return picture.toString();
	}
}