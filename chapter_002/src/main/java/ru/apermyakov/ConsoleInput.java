package ru.apermyakov;

import java.util.List;
import java.util.Scanner;

/**
* Class for console input.
*
* @author apermyakov
* @since 16.10.2017
* @version 1.0
*/
public class ConsoleInput implements Input {

	/**
	* Feild for user input.
	*
	*/
	private Scanner scanner = new Scanner(System.in);

	/**
	* Method for asking user and get result.
	*
	* @author apermyakov
	* @since 16.10.2017
	* @param question asking user somesing
	* @return user choice
	* @version 1.0
	*/
	public String ask(String question) {
		System.out.print(question);
		return scanner.nextLine();
	}

	/**
	* Method for asking user and get result.
	*
	* @author apermyakov
	* @since 17.10.2017
	* @param question asking user somesing
	* @param range range of menu
	* @return user choice
	* @version 1.0
	*/
	public int ask(String question, List<Integer> range) {
		int key = Integer.valueOf(this.ask(question));
		boolean exist = false;
		for (int value : range) {
			if (value == key) {
				exist = true;
				break;
			}
		}
		if (exist) {
			return key;
		} else {
			throw new MenuOutException("Out of menu range. ");
		}
	}
}