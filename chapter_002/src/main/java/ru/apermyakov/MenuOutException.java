package ru.apermyakov;

/**
* Class for input exceptions.
*
* @author apermyakov
* @since 18.10.2017
* @version 1.0
*/
public class MenuOutException extends RuntimeException {

	/**
	* Design input exceptions.
	*
	* @param msg string with exception
	*/
	public MenuOutException(String msg) {
		super(msg);
	}
}