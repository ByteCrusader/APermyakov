package ru.apermyakov;

/**
 * Class for create items.
 *
 * @author apermyakov
 * @version 1.0
 * @since 13.10.2017
 */
public class Item {

	/**
	* Feild item id.
	*/
	private String id;

	/**
	* Feild name of task.
	*/
	private String name;

	/**
	* Feild description of task.
	*/
	private String description;

	/**
	* Feild data when task was created.
	*/
	private long created;

	/**
	* Feild array of comments to items.
	*/
	private String[] comments;

	/**
	* Feild counter/length of comments array.
	*/
	private int commentPosition = 0;

	/**
	* Design Item.
	*
	* @param name Name of task
	* @param description is descriprion of task
	* @param created when task was created
	*/
	public Item(String name, String description, long created) {
		this.name = name;
		this.description = description;
		this.created = created;
	}

	/**
	* Method get item's id.
	* @return item's id
	*/
	public String getId() {
		return this.id;
	}

	/**
	* Method set item's id.
	* @param id item's id
	*/
	public void setId(String id) {
		this.id = id;
	}

	/**
	* Method get item's name.
	* @return item's name
	*/
	public String getName() {
		return this.name;
	}

	/**
	* Method get item's description.
	* @return item's id
	*/
	public String getDescription() {
		return this.description;
	}

	/**
	* Method get item's date of create.
	* @return item's date of create
	*/
	public long getCreated() {
		return this.created;
	}

	/**
	* Method add item's comments.
	* @param comment item's comment
	*/
	public void addComment(String comment) {
		this.comments[commentPosition] = comment;
		commentPosition++;
	}

	/**
	* Method get item's comments array.
	* @return item's comments array
	*/
	public String[] getComments() {
		return this.comments;
	}
}