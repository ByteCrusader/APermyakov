package ru.apermyakov;

/**
* Interface for all menu classes.
*
* @author apermyakov
* @version 1.0
* @since 17.10.2017
*/
public interface UserAction {

	/**
	* Method for initial class menu key.
	*
	* @author apermyakov
	* @return menu key
	* @since 17.10.2017
	*/
	int key();

	/**
	* Method for main activity of class.
	*
	* @author apermyakov
	* @param input user input
	* @param tracker array of items
	* @since 17.10.2017
	*/
	void mainActivity(Input input, Tracker tracker);

	/**
	* Method for print class menu assignment.
	*
	* @author apermyakov
	* @return class menu assignment
	* @since 17.10.2017
	*/
	String info();
}