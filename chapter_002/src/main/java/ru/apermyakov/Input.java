package ru.apermyakov;

/**
* Interface for input information.
*
* @author apermyakov
* @since 16.10.2017
* @version 1.0
*/
public interface Input {

	/**
	* Method for console input.
	*
	* @author apermyakov
	* @since 16.10.2017
	* @param question request
	* @return choice
	* @version 1.0
	*/
	String ask(String question);
}