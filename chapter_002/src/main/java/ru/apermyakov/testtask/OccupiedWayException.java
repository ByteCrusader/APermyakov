package ru.apermyakov.testtask;

/**
* Class for chess exceptions.
*
* @author apermyakov
* @since 20.10.2017
* @version 1.0
*/
public class OccupiedWayException extends RuntimeException {

	/**
	* Design input exceptions.
	*
	* @param message string with exception
	*/
	public OccupiedWayException(String message) {
		super(message);
	}
}