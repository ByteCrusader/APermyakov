package ru.apermyakov;

public class StartUI {
	
	private Tracker tracker = new Tracker();
	private static int work = 1;
	private static final String ADD = "0";
	private static final String SHOW = "1";
	private static final String EDIT = "2";
	private static final String DELETE = "3";
	private static final String FINDID = "4";
	private static final String FINDNAME = "5";
	private static final String EXIT = "6";
	
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
	
	public void addItem(ConsoleInput input) {
		String name = input.ask("Please, input your name: ");
		String desc = input.ask("Please, input description of task: ");
		tracker.add(new Item(name, desc, 123L));
	}
	
	public void showAll() {
		for(Item item : tracker.findAll()) {
			System.out.println(String.format("Id: %s; name: %s; description: %s", item.getId(), item.getName(), item.getDescription()));
		}	
	}
	
	public void editItem(ConsoleInput input) {
		String baseId = input.ask("Please, input id of task, which you want to edit: ");
		String newName = input.ask("Please, input new name of task: ");
		String newDesc = input.ask("Please, input new description of task: ");
		Item newItem = new Item(newName, newDesc, 123L);
		newItem.setId(baseId);
		tracker.update(newItem);
	}
	
	public void deleteItem(ConsoleInput input) {
		String baseId = input.ask("Please, input id of task, which you want to delete: ");
		tracker.delete(tracker.findById(baseId));
	}
	
	public void findId(ConsoleInput input) {
		String baseId = input.ask("Please, input id of task, which you want to find: ");
		Item item = tracker.findById(baseId);
		System.out.println(String.format("Id: %s; name: %s; description: %s", item.getId(), item.getName(), item.getDescription()));
	}
	
	public void findName(ConsoleInput input) {
		String name = input.ask("Please, input name of tasks, which you want to find: ");
		Item[] results = tracker.findByName(name);
		for(Item item : results) {
			System.out.println(String.format("Id: %s; name: %s; description: %s", item.getId(), item.getName(), item.getDescription()));
		}
	}
	
	public void initial() {
		while(work == 1) {
			ConsoleInput input = new ConsoleInput();
			String name = input.ask(this.menu());
			switch (name) {
				case ADD: 
					this.addItem(input);
					break;
				case SHOW:
					this.showAll();
					break;
				case EDIT:
					this.editItem(input);
					break;
				case DELETE:
					this.deleteItem(input);
					break;
				case FINDID:
					this.findId(input);
					break;
				case FINDNAME:
					this.findName(input);
					break;
				case EXIT:
					System.out.println("bye");
					work = 0;
					break;
			}
		}
	}
	
	public static void main(String[] args) {
			new StartUI().initial();
	}
}