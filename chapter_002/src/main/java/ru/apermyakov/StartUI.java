package ru.apermyakov;

/**
* Class for run application.
*
* @author apermyakov
* @since 16.10.2017
* @version 1.0
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
		int chacker = 0;
		do {
			menu.initial();
			menu.drow();
			int key = Integer.valueOf(input.ask("Please, select meny's item: "));
			menu.choise(key);
			if (key == 6) {
				chacker++;
			}
		} while (chacker == 0);
	}

	/**
	* Method for initial application.
	*
	* @author apermyakov
	* @param args args
	* @since 16.10.2017
	*/
	public static void main(String[] args) {
		Input input = new ConsoleInput();
		Tracker tracker = new Tracker();
		new StartUI(input, tracker).initial();
	}
}