package ru.apermyakov;

/**
* Abstract class for all menu classes.
*
* @author apermyakov
* @version 1.0
* @since 18.10.2017
*/
public abstract class BaseAction implements UserAction {

	/**
	* Initial menu name.
	*/
	private String name;

	/**
	* Initial menu key.
	*/
	private int key;

	/**
	* Design abstract class.
	*
	* @author apermyakov
	* @param key menu key
	* @param name menu name
	*/
	public BaseAction(int key, String name) {
		this.name = name;
		this.key = key;
	}

	/**
	* Method for print class menu assignment.
	*
	* @author apermyakov
	* @return class menu assignment
	* @since 18.10.2017
	*/
	public int key() {
		return this.key;
	}

	/**
	* Method for print class menu assignment.
	*
	* @author apermyakov
	* @return class menu assignment
	* @since 18.10.2017
	*/
	public String info() {
		return String.format("%s. %s", this.key, this.name);
	}
}