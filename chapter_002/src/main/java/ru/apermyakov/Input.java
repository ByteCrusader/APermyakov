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
	* Method for string input.
	*
	* @author apermyakov
	* @since 16.10.2017
	* @param question request
	* @return choice
	* @version 1.0
	*/
	String ask(String question);

	/**
	* Method for integer input.
	*
	* @author apermyakov
	* @since 17.10.2017
	* @param question request
	* @param range range of menu
	* @return choice
	* @version 1.0
	*/
	int ask(String question, int[] range);
}