package ru.apermyakov;

import java.util.ArrayList;
import java.util.List;
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
	 * Field array of items.
	 */
	private List<Item> items = new ArrayList<>();

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
		this.items.add(item);
		return item;
	}

	/**
	* Method update item.
	* @param item new item
	*/
	public void update(Item item) {
		int index = 0;
		for (Item value : this.items) {
			if (value.getId().equals(item.getId()) && value != null) {
				this.items.set(index, item);
				break;
			}
			index++;
		}
	}

	/**
	* Method delete item.
	* @param item chousen item
	*/
	public void delete(Item item) {
		int index = 0;
		for (Item value : this.items) {
			if (value.getId().equals(item.getId()) && value != null && items.size() > 1) {
				this.items.remove(index);
				break;
			}
			index++;
		}
	}

	/**
	* Method find all item whithout null.
	* @return all item
	*/
	public List<Item> findAll() {
		List<Item> result = new ArrayList<>();
		for (Item value : this.items) {
			result.add(value);
		}
		return result;
	}

	/**
	* Method find item by name.
	* @param key item's name
	* @return array of found names
	*/
	public List<Item> findByName(String key) {
		List<Item> result = new ArrayList<>();
		for (Item value : this.items) {
			if (value != null && value.getName().equals(key)) {
				result.add(value);
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