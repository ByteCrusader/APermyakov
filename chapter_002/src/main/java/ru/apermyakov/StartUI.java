package ru.apermyakov;

import java.util.ArrayList;
import java.util.List;

/**
* Class for run application.
*
* @author apermyakov
* @since 16.10.2017
* @version 1.1
*/
public class StartUI {

	/**
	* Initial input info.
	*/
	private Input input;

	/**
	* Initial start application.
	*
	* @param input input info
	* @param tracker array of items
	* @since 16.10.2017
	*/
	public StartUI(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}

	/**
	* Initial tracker.
	*/
	private Tracker tracker;

	/**
	* Method for waiting user input.
	*
	* @author apermyakov
	* @since 17.10.2017
	*/
	public void initial() {
		MenuTracker menu = new MenuTracker(this.input, tracker);
		menu.initial();
		List<Integer> ranges = new ArrayList<>();
		for (UserAction action : menu.getActions()) {
			ranges.add(action.key());
		}
		do {
			menu.drow();
			int key = input.ask("Please, select meny's item: ", ranges);
			menu.choise(key);
			if (key == menu.getActions().get(menu.getActions().size() - 1).key()) {
				break;
			}
		} while (true);
	}

	/**
	* Method for initial application.
	*
	* @author apermyakov
	* @param args args
	* @since 16.10.2017
	*/
	public static void main(String[] args) {
		Input input = new ValidateInput();
		Tracker tracker = new Tracker();
		tracker.initial(true);
		new StartUI(input, tracker).initial();
	}
}