package ru.apermyakov;

import java.util.List;

/**
* Class for auto output info.
*
* @author apermyakov
* @since 16.10.2017
* @version 1.0
*/
public class StubInput implements Input {
	/**
	* Initial output info.
	*/
	private String[] answers;
	/**
	* Initial position of output info array.
	*/
	private int position = 0;

	/**
	* Design auto output info.
	*
	* @param answers array of output info
	*/
	public StubInput(String[] answers) {
		this.answers = answers;
	}

	/**
	* Method for sending auto output info.
	*
	* @author apermyakov
	* @param question input info
	* @return auto output info
	* @since 16.10.2017
	*/
	public String ask(String question) {
		return answers[position++];
	}

	/**
	* Method for sending auto output info.
	*
	* @author apermyakov
	* @param question input info
	* @param range range of menu
	* @return auto output info
	* @since 17.10.2017
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