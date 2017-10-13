package ru.apermyakov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for test tracker.
 *
 * @author apermyakov
 * @version 1.0
 * @since 13.10.2017
 */
public class TrackerTest {

	/**
	* Test when add new item then find this item in array.
	*/
	@Test
	public void whenAddNewItemThenAdd() {
		Tracker tracker = new Tracker();
		Item item = new Item("TestAAA", "aaa", 123L);
		tracker.add(item);
		assertThat(tracker.findAll()[0], is(item));
	}

	/**
	* Test when update item then by old id new item.
	*/
	@Test
	public void whenUpdateItemThenUpdateCell() {
		Tracker tracker = new Tracker();
		Item first = new Item("Firts", "1", 123L);
		tracker.add(first);
		Item second = new Item("Second", "2", 123L);
		second.setId(first.getId());
		tracker.update(second);
		assertThat(tracker.findById(second.getId()).getName(), is("Second"));
	}

	/**
	* Test when delete item then null in that place.
	*/
	@Test
	public void whenDeleteItemThenDeleteCell() {
		Tracker tracker = new Tracker();
		Item first = new Item("Firts", "1", 123L);
		tracker.add(first);
		Item expext = null;
		tracker.delete(first);
		assertThat(tracker.findAll()[0], is(expext));
	}

	/**
	* Test when get all items without null then take length of all not null items.
	*/
	@Test
	public void whenNeedFindAllThenLengthOfResultArray() {
		Tracker tracker = new Tracker();
		Item first = new Item("First", "1", 123L);
		tracker.add(first);
		Item second = new Item("Second", "2", 1234L);
		tracker.add(second);
		Item third = new Item("Third", "3", 122L);
		tracker.add(third);
		assertThat(tracker.findAll().length, is(3));
	}

	/**
	* Test when find item by name then take length of all items with this name.
	*/
	@Test
	public void whenFindByNameItemLengthOfResultArray() {
		Tracker tracker = new Tracker();
		Item first = new Item("Firts", "1", 123L);
		tracker.add(first);
		Item second = new Item("First", "2", 123L);
		tracker.add(second);
		assertThat(tracker.findByName(second.getName()).length, is(2));
	}

	/**
	* Test when find item by id then take item or null.
	*/
	@Test
	public void whenFindByIdItemThenItemOrNull() {
		Tracker tracker = new Tracker();
		Item first = new Item("Firts", "1", 123L);
		tracker.add(first);
		String itemId = first.getId();
		Item second = new Item("First", "2", 123L);
		tracker.add(second);
		tracker.delete(first);
		Item expext = null;
		assertThat(tracker.findById(itemId), is(expext));
	}
}