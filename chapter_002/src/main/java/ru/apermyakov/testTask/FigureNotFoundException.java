package ru.apermyakov.testtask;

/**
* Class for chess exceptions.
*
* @author apermyakov
* @since 20.10.2017
* @version 1.0
*/
public class FigureNotFoundException extends RuntimeException {

	/**
	* Design input exceptions.
	*
	* @param messege string with exception
	*/
	public FigureNotFoundException(String messege) {
		super(messege);
	}
}