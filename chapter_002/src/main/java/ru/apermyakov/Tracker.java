package ru.apermyakov;

import java.util.Random;


/**
 * Class for create tracker.
 *
 * @author apermyakov
 * @version 1.0
 * @since 13.10.2017
 */
public class Tracker {

	/**
	* Feild array of items.
	*/
	private Item[] items = new Item[100];

	/**
	* Feild item index for arrays.
	*/
	private int itemsIndex = 0;

	/**
	* Feild for set uniq id.
	*/
	private static final Random RN = new Random();

	/**
	* Method add item for tracker.
	* @param item new item
	* @return new item
	*/
	public Item add(Item item) {
		item.setId(String.valueOf(System.currentTimeMillis() + RN.nextInt()));
		this.items[itemsIndex] = item;
		itemsIndex++;
		return item;
	}

	/**
	* Method update item.
	* @param item new item
	*/
	public void update(Item item) {
		for (int index = 0; index < itemsIndex; index++) {
			if (this.items[index].getId().equals(item.getId()) && this.items[index] != null) {
				this.items[index] = item;
				break;
			}
		}
	}

	/**
	* Method delete item.
	* @param item chousen item
	*/
	public void delete(Item item) {
		for (int index = 0; index < itemsIndex; index++) {
			if (this.items[index].getId().equals(item.getId()) && this.items[index] != null) {
				this.items[index] = null;
				break;
			}
		}
	}

	/**
	* Method find all item whithout null.
	* @return all item
	*/
	public Item[] findAll() {
		Item[] result = new Item[itemsIndex];
		for (int index = 0; index < itemsIndex; index++) {
				result[index] = this.items[index];
		}
		return result;
	}

	/**
	* Method find item by name.
	* @param key item's name
	* @return array of found names
	*/
	public Item[] findByName(String key) {
		Item[] result = new Item[itemsIndex];
		int inIndex = 0;
		for (int index = 0; index < itemsIndex; index++) {
			if (items[index] != null && this.items[index].getName().equals(key)) {
				result[inIndex] = this.items[index];
				inIndex++;
			}
		}
		return result;
	}

	/**
	* Method find item by Id.
	* @param id item's id
	* @return found item
	*/
	public Item findById(String id) {
		Item result = null;
		for (Item item : this.items) {
			if (item != null && item.getId().equals(id)) {
				result = item;
				break;
			}
		}
		return result;
	}
}