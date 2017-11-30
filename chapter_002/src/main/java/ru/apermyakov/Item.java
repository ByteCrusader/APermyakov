package ru.apermyakov;

import java.util.List;

/**
 * Class for create items.
 *
 * @author apermyakov
 * @version 1.1
 * @since 13.10.2017
 */
public class Item {

	/**
	* Field item id.
	*/
	private int id;

	/**
	* Field name of task.
	*/
	private String name;

	/**
	* Field description of task.
	*/
	private String description;

	/**
	* Field data when task was created.
	*/
	private long created;

	/**
	* Field array of comments to items.
	*/
	private List<String> comments;

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
	public int getId() {
		return this.id;
	}

	/**
	* Method set item's id.
	* @param id item's id
	*/
	public void setId(int id) {
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
		this.comments.add(comment);
	}

	/**
	* Method get item's comments array.
	* @return item's comments array
	*/
	public List<String> getComments() {
		return this.comments;
	}
}