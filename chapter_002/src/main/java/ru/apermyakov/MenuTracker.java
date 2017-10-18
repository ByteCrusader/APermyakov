package ru.apermyakov;

/**
* Class for add Item.
*
* @author apermyakov
* @version 1.0
* @since 17.10.2017
*/
class Add extends BaseAction {

	/**
	* Design event add.
	*
	* @author apermyakov
	* @param key key of menu
	* @param name name of menu
	* @since 18.10.2017
	*/
	Add(int key, String name) {
		super(key, name);
	}

	/**
	* Method for main activity of class.
	*
	* @author apermyakov
	* @param input user input
	* @param tracker array of items
	* @since 17.10.2017
	*/
	public void mainActivity(Input input, Tracker tracker) {
		String name = input.ask("Please, input your name: ");
		String desc = input.ask("Please, input description of task: ");
		tracker.add(new Item(name, desc, 123L));
	}
}

/**
* Class for edit Item.
*
* @author apermyakov
* @version 1.0
* @since 17.10.2017
*/
class Edit extends BaseAction {

	/**
	* Design event edit.
	*
	* @author apermyakov
	* @param key key of menu
	* @param name name of menu
	* @since 18.10.2017
	*/
	Edit(int key, String name) {
		super(key, name);
	}

	/**
	* Method for main activity of class.
	*
	* @author apermyakov
	* @param input user input
	* @param tracker array of items
	* @since 17.10.2017
	*/
	public void mainActivity(Input input, Tracker tracker) {
		String baseId = input.ask("Please, input id of task, which you want to edit: ");
		String newName = input.ask("Please, input new name of task: ");
		String newDesc = input.ask("Please, input new description of task: ");
		Item newItem = new Item(newName, newDesc, 123L);
		newItem.setId(baseId);
		tracker.update(newItem);
	}
}

/**
* Class for delete Item.
*
* @author apermyakov
* @version 1.0
* @since 17.10.2017
*/
class Delete extends BaseAction {

	/**
	* Design event delete.
	*
	* @author apermyakov
	* @param key key of menu
	* @param name name of menu
	* @since 18.10.2017
	*/
	Delete(int key, String name) {
		super(key, name);
	}

	/**
	* Method for main activity of class.
	*
	* @author apermyakov
	* @param input user input
	* @param tracker array of items
	* @since 17.10.2017
	*/
	public void mainActivity(Input input, Tracker tracker) {
		String baseId = input.ask("Please, input id of task, which you want to delete: ");
		tracker.delete(tracker.findById(baseId));
	}
}

/**
* Class for initial menu.
*
* @author apermyakov
* @version 1.0
* @since 17.10.2017
*/
public class MenuTracker {

	/**
	* Initial input info.
	*/
	private Input input;

	/**
	* Initial tracker.
	*/
	private Tracker tracker;

	/**
	* Initial menu.
	*/
	private UserAction[] actions = new UserAction[7];

	/**
	* Design menu tracker.
	*
	* @param input user input
	* @param tracker array of items
	*/
	public MenuTracker(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}

	/**
	* Initial menu's classes.
	*/
	public void initial() {
		this.actions[0] = new Add(0, "Add new item. ");
		this.actions[1] = new Show(1, "Show all items. ");
		this.actions[2] = new Edit(2, "Edit item. ");
		this.actions[3] = new Delete(3, "Delete item. ");
		this.actions[4] = new FindId(4, "Find item by ID. ");
		this.actions[5] = new FindName(5, "Find items by name. ");
		this.actions[6] = new Exit(6, "Exit. ");
	}

	/**
	* Get menu's array.
	*
	* @author apermyakov
	* @return array of menu
	* @since 18.10.2017
	*/
	public UserAction[] getActions() {
		return this.actions;
	}

	/**
	* Method for choise class menu assignment.
	*
	* @author apermyakov
	* @param key menu assignment
	* @since 17.10.2017
	*/
	public void choise(int key) {
		for (UserAction act : actions) {
			if (act.key() == key) {
				act.mainActivity(this.input, this.tracker);
			}
		}
	}

	/**
	* Method for print class menu assignment.
	*
	* @author apermyakov
	* @since 17.10.2017
	*/
	public void drow() {
		for (UserAction action : this.actions) {
			if (action != null) {
				System.out.println(action.info());
			}
		}
	}

	/**
	* Class for show Items.
	*
	* @author apermyakov
	* @version 1.0
	* @since 17.10.2017
	*/
	private static class Show extends BaseAction {

		/**
		* Design event show.
		*
		* @author apermyakov
		* @param key key of menu
		* @param name name of menu
		* @since 18.10.2017
		*/
		Show(int key, String name) {
			super(key, name);
		}

		/**
		* Method for main activity of class.
		*
		* @author apermyakov
		* @param input user input
		* @param tracker array of items
		* @since 17.10.2017
		*/
		public void mainActivity(Input input, Tracker tracker) {
			for (Item item : tracker.findAll()) {
				System.out.println(String.format("Id: %s; name: %s; description: %s", item.getId(), item.getName(), item.getDescription()));
			}
		}
	}

	/**
	* Class for find Item by Id.
	*
	* @author apermyakov
	* @version 1.0
	* @since 17.10.2017
	*/
	private class FindId extends BaseAction {

		/**
		* Design event find by id.
		*
		* @author apermyakov
		* @param key key of menu
		* @param name name of menu
		* @since 18.10.2017
		*/
		FindId(int key, String name) {
			super(key, name);
		}

		/**
		* Method for main activity of class.
		*
		* @author apermyakov
		* @param input user input
		* @param tracker array of items
		* @since 17.10.2017
		*/
		public void mainActivity(Input input, Tracker tracker) {
			String baseId = input.ask("Please, input id of task, which you want to find: ");
			Item item = tracker.findById(baseId);
			System.out.println(String.format("Id: %s; name: %s; description: %s", item.getId(), item.getName(), item.getDescription()));
		}
	}

	/**
	* Class for find Items by name.
	*
	* @author apermyakov
	* @version 1.0
	* @since 17.10.2017
	*/
	private class FindName extends BaseAction {

		/**
		* Design event find by name.
		*
		* @author apermyakov
		* @param key key of menu
		* @param name name of menu
		* @since 18.10.2017
		*/
		FindName(int key, String name) {
			super(key, name);
		}

		/**
		* Method for main activity of class.
		*
		* @author apermyakov
		* @param input user input
		* @param tracker array of items
		* @since 17.10.2017
		*/
		public void mainActivity(Input input, Tracker tracker) {
			String name = input.ask("Please, input name of tasks, which you want to find: ");
			Item[] results = tracker.findByName(name);
			for (Item item : results) {
				System.out.println(String.format("Id: %s; name: %s; description: %s", item.getId(), item.getName(), item.getDescription()));
			}
		}
	}

	/**
	* Class for go out of application.
	*
	* @author apermyakov
	* @version 1.0
	* @since 17.10.2017
	*/
	private static class Exit extends BaseAction {

		/**
		* Design event exit.
		*
		* @author apermyakov
		* @param key key of menu
		* @param name name of menu
		* @since 18.10.2017
		*/
		Exit(int key, String name) {
			super(key, name);
		}

		/**
		* Method for main activity of class.
		*
		* @author apermyakov
		* @param input user input
		* @param tracker array of items
		* @since 17.10.2017
		*/
		public void mainActivity(Input input, Tracker tracker) {
			System.out.println("Thanks for using!");
		}
	}
}