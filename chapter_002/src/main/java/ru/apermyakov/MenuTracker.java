package ru.apermyakov;

/**
* Class for add Item.
*
* @author apermyakov
* @version 1.0
* @since 17.10.2017
*/
class Add implements UserAction {

	/**
	* Method for initial class menu key.
	*
	* @author apermyakov
	* @return menu key
	* @since 17.10.2017
	*/
	public int key() {
		return 0;
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

	/**
	* Method for print class menu assignment.
	*
	* @author apermyakov
	* @return class menu assignment
	* @since 17.10.2017
	*/
	public String info() {
		return String.format("%s. %s", this.key(), "Add the new item. ");
	}
}

/**
* Class for edit Item.
*
* @author apermyakov
* @version 1.0
* @since 17.10.2017
*/
class Edit implements UserAction {

	/**
	* Method for initial class menu key.
	*
	* @author apermyakov
	* @return menu key
	* @since 17.10.2017
	*/
	public int key() {
		return 2;
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

	/**
	* Method for print class menu assignment.
	*
	* @author apermyakov
	* @return class menu assignment
	* @since 17.10.2017
	*/
	public String info() {
		return String.format("%s. %s", this.key(), "Edit item. ");
	}
}

/**
* Class for delete Item.
*
* @author apermyakov
* @version 1.0
* @since 17.10.2017
*/
class Delete implements UserAction {

	/**
	* Method for initial class menu key.
	*
	* @author apermyakov
	* @return menu key
	* @since 17.10.2017
	*/
	public int key() {
		return 3;
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

	/**
	* Method for print class menu assignment.
	*
	* @author apermyakov
	* @return class menu assignment
	* @since 17.10.2017
	*/
	public String info() {
		return String.format("%s. %s", this.key(), "Delete item. ");
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
		this.actions[0] = new Add();
		this.actions[1] = new Show();
		this.actions[2] = new Edit();
		this.actions[3] = new Delete();
		this.actions[4] = new FindId();
		this.actions[5] = new FindName();
		this.actions[6] = new Exit();
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
		this.actions[key].mainActivity(this.input, this.tracker);
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
	private static class Show implements UserAction {

		/**
		* Method for initial class menu key.
		*
		* @author apermyakov
		* @return menu key
		* @since 17.10.2017
		*/
		public int key() {
			return 1;
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

		/**
		* Method for print class menu assignment.
		*
		* @author apermyakov
		* @return class menu assignment
		* @since 17.10.2017
		*/
		public String info() {
			return String.format("%s. %s", this.key(), "Show all items. ");
		}
	}

	/**
	* Class for find Item by Id.
	*
	* @author apermyakov
	* @version 1.0
	* @since 17.10.2017
	*/
	private class FindId implements UserAction {

		/**
		* Method for initial class menu key.
		*
		* @author apermyakov
		* @return menu key
		* @since 17.10.2017
		*/
		public int key() {
			return 4;
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

		/**
		* Method for print class menu assignment.
		*
		* @author apermyakov
		* @return class menu assignment
		* @since 17.10.2017
		*/
		public String info() {
			return String.format("%s. %s", this.key(), "Find item by Id. ");
		}
	}

	/**
	* Class for find Items by name.
	*
	* @author apermyakov
	* @version 1.0
	* @since 17.10.2017
	*/
	private class FindName implements UserAction {

		/**
		* Method for initial class menu key.
		*
		* @author apermyakov
		* @return menu key
		* @since 17.10.2017
		*/
		public int key() {
			return 5;
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

		/**
		* Method for print class menu assignment.
		*
		* @author apermyakov
		* @return class menu assignment
		* @since 17.10.2017
		*/
		public String info() {
			return String.format("%s. %s", this.key(), "Find items by name. ");
		}
	}

	/**
	* Class for add Item.
	*
	* @author apermyakov
	* @version 1.0
	* @since 17.10.2017
	*/
	private static class Exit implements UserAction {

		/**
		* Method for initial class menu key.
		*
		* @author apermyakov
		* @return menu key
		* @since 17.10.2017
		*/
		public int key() {
			return 6;
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

		/**
		* Method for print class menu assignment.
		*
		* @author apermyakov
		* @return class menu assignment
		* @since 17.10.2017
		*/
		public String info() {
			return String.format("%s. %s", this.key(), "Exit. ");
		}
	}
}