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
	* Const for menu add.
	*/
	private static final String ADD = "0";
	/**
	* Const for menu show.
	*/
	private static final String SHOW = "1";
	/**
	* Const for menu edit.
	*/
	private static final String EDIT = "2";
	/**
	* Const for menu delete.
	*/
	private static final String DELETE = "3";
	/**
	* Const for menu find by id.
	*/
	private static final String FINDID = "4";
	/**
	* Const for menu find by name.
	*/
	private static final String FINDNAME = "5";
	/**
	* Const for menu exit.
	*/
	private static final String EXIT = "6";

	/**
	* Method for build menu.
	*
	* @author apermyakov
	* @since 16.10.2017
	* @return menu
	*/
	public String menu() {
		StringBuilder string = new StringBuilder();
		string.append("0. Add new item");
		string.append(System.getProperty("line.separator"));
		string.append("1. Show all items");
		string.append(System.getProperty("line.separator"));
		string.append("2. Edit item");
		string.append(System.getProperty("line.separator"));
		string.append("3. Delete item");
		string.append(System.getProperty("line.separator"));
		string.append("4. Find item by Id");
		string.append(System.getProperty("line.separator"));
		string.append("5. Find items by name");
		string.append(System.getProperty("line.separator"));
		string.append("6. Exit program");
		string.append(System.getProperty("line.separator"));
		string.append("Please, select meny's item: ");
		return string.toString();
	}

	/**
	* Method for add Item.
	*
	* @author apermyakov
	* @param input user input
	* @since 16.10.2017
	*/
	public void addItem(Input input) {
		String name = input.ask("Please, input your name: ");
		String desc = input.ask("Please, input description of task: ");
		tracker.add(new Item(name, desc, 123L));
	}

	/**
	* Method for show all item.
	*
	* @author apermyakov
	* @since 16.10.2017
	*/
	public void showAll() {
		for (Item item : tracker.findAll()) {
			System.out.println(String.format("Id: %s; name: %s; description: %s", item.getId(), item.getName(), item.getDescription()));
		}
	}

	/**
	* Method for edit item.
	*
	* @author apermyakov
	* @param input user input
	* @since 16.10.2017
	*/
	public void editItem(Input input) {
		String baseId = input.ask("Please, input id of task, which you want to edit: ");
		String newName = input.ask("Please, input new name of task: ");
		String newDesc = input.ask("Please, input new description of task: ");
		Item newItem = new Item(newName, newDesc, 123L);
		newItem.setId(baseId);
		tracker.update(newItem);
	}

	/**
	* Method for delete item.
	*
	* @author apermyakov
	* @param input user input
	* @since 16.10.2017
	*/
	public void deleteItem(Input input) {
		String baseId = input.ask("Please, input id of task, which you want to delete: ");
		tracker.delete(tracker.findById(baseId));
	}

	/**
	* Method for find by id.
	*
	* @author apermyakov
	* @param input user input
	* @since 16.10.2017
	*/
	public void findId(Input input) {
		String baseId = input.ask("Please, input id of task, which you want to find: ");
		Item item = tracker.findById(baseId);
		System.out.println(String.format("Id: %s; name: %s; description: %s", item.getId(), item.getName(), item.getDescription()));
	}

	/**
	* Method for find by name.
	*
	* @author apermyakov
	* @param input user input
	* @since 16.10.2017
	*/
	public void findName(Input input) {
		String name = input.ask("Please, input name of tasks, which you want to find: ");
		Item[] results = tracker.findByName(name);
		for (Item item : results) {
			System.out.println(String.format("Id: %s; name: %s; description: %s", item.getId(), item.getName(), item.getDescription()));
		}
	}

	/**
	* Method for waiting user input.
	*
	* @author apermyakov
	* @since 16.10.2017
	*/
	public void initial() {
		while (true) {
			String name = input.ask(this.menu());
			if (ADD.equals(name)) {
				this.addItem(input);
			} else if (ADD.equals(name)) {
				this.addItem(input);
			} else if (SHOW.equals(name)) {
				this.showAll();
			} else if (EDIT.equals(name)) {
				this.editItem(input);
			} else if (DELETE.equals(name)) {
				this.deleteItem(input);
			} else if (FINDID.equals(name)) {
				this.findId(input);
			} else if (FINDNAME.equals(name)) {
				this.findName(input);
			} else if (EXIT.equals(name)) {
				System.out.println("Bye");
				break;
			} else {
				System.out.println("Invalid entered item, please write whole number");
			}
		}
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