package ru.apermyakov;

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
}