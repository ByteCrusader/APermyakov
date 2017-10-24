package ru.apermyakov;

import java.util.List;

/**
* Class for validate input.
*
* @author apermyakov
* @since 16.10.2017
* @version 1.0
*/
public class ValidateInput extends ConsoleInput {

	/**
	* Method for check input info.
	*
	* @author apermyakov
	* @param question input info
	* @param range range of menu
	* @return validate info or exception
	* @since 17.10.2017
	*/
	public int ask(String question, List<Integer> range) {
		int value = -1;
		do {
			try {
				value = super.ask(question, range);
				break;
			} catch (MenuOutException m) {
				System.out.println("Please, select key from menu. ");
			} catch (NumberFormatException e) {
				System.out.println("Please, enter validate data again. ");
			}
		} while (true);
		return value;
	}
}