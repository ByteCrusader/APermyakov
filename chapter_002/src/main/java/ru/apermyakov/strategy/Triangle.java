package ru.apermyakov.strategy;

/**
* Class for paint triangle shape.
*
* @author apermyakov
* @since 16.10.2017
* @version 1.0
*/
public class Triangle implements Shape {

	/**
	* Class for build triangle shape.
	*
	* @author apermyakov
	* @since 16.10.2017
	* @version 1.0
	* @return build triangle to string
	*/
	public String pic() {
		StringBuilder picture = new StringBuilder();
		picture.append("+++++++");
		picture.append(System.getProperty("line.separator"));
		picture.append("+++x+++");
		picture.append(System.getProperty("line.separator"));
		picture.append("++xxx++");
		picture.append(System.getProperty("line.separator"));
		picture.append("+xxxxx+");
		picture.append(System.getProperty("line.separator"));
		picture.append("+++++++");
		return picture.toString();
	}
}