package ru.apermyakov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for test tracker.
 *
 * @author apermyakov
 * @version 1.1
 * @since 13.10.2017
 */
public class TrackerTest {

	/**
	* Test when add new item then find this item in array.
	*/
	@Test
	public void whenAddNewItemThenAdd() {
		Tracker tracker = new Tracker();
		tracker.initial(true);
		Item item = new Item("TestAAA", "aaa", 123L);
		tracker.add(item);
		assertThat(tracker.findAll().get(0).getName(), is(item.getName()));
	}

	/**
	* Test when update item then by old id new item.
	*/
	@Test
	public void whenUpdateItemThenUpdateCell() {
		Tracker tracker = new Tracker();
		tracker.initial(true);
		Item first = new Item("First", "1", 123L);
		tracker.add(first);
		Item second = new Item("Second", "2", 123L);
		second.setId(first.getId());
		tracker.update(second);
		assertThat(tracker.findById(Integer.valueOf(second.getId())).getName(), is("Second"));
	}

	/**
	* Test when delete item then null in that place.
	*/
	@Test
	public void whenDeleteItemThenDeleteCell() {
		Tracker tracker = new Tracker();
		tracker.initial(true);
		Item first = new Item("First", "1", 123L);
		tracker.add(first);
		Item second = new Item("Second", "2", 123L);
		tracker.add(second);
		tracker.delete(first);
		assertThat(tracker.findAll().get(0).getName(), is(second.getName()));
	}

	/**
	* Test when get all items without null then take length of all not null items.
	*/
	@Test
	public void whenNeedFindAllThenLengthOfResultArray() {
		Tracker tracker = new Tracker();
		tracker.initial(true);
		Item first = new Item("First", "1", 123L);
		tracker.add(first);
		Item second = new Item("Second", "2", 1234L);
		tracker.add(second);
		Item third = new Item("Third", "3", 122L);
		tracker.add(third);
		assertThat(tracker.findAll().size(), is(3));
	}

	/**
	* Test when find item by name then take length of all items with this name.
	*/
	@Test
	public void whenFindByNameItemLengthOfResultArray() {
		Tracker tracker = new Tracker();
		tracker.initial(true);
		Item first = new Item("First", "1", 123L);
		tracker.add(first);
		Item second = new Item("First", "2", 123L);
		tracker.add(second);
		assertThat(tracker.findByName(second.getName()).size(), is(2));
	}

	/**
	* Test when find item by id then take item or null.
	*/
	@Test(expected = IllegalArgumentException.class)
	public void whenFindByIdItemThenItemOrEx() {
		Tracker tracker = new Tracker();
		tracker.initial(true);
		Item first = new Item("First", "1", 123L);
		tracker.add(first);
		int itemId = Integer.valueOf(first.getId());
		Item second = new Item("First", "2", 123L);
		tracker.add(second);
		tracker.delete(first);
		tracker.findById(itemId);
	}

	/**
	 * Test when add new item to database then add.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void whenAddNewItemToDbThenAdd() {
		Tracker tracker = new Tracker();
		Item first = new Item("First", "1", 123L);
		Item second = new Item("Second", "2", 123L);
		tracker.initial(true);
		tracker.add(first);
		tracker.add(second);
		assertThat(tracker.findAll().get(0).getName(), is(first.getName()));
		second.setId(first.getId());
		tracker.update(second);
		assertThat(tracker.findAll().get(0).getName(), is(second.getName()));
		assertThat(tracker.findByName(second.getName()).get(1).getName(), is(second.getName()));
		tracker.delete(second);
		assertThat(tracker.findAll().get(0).getId(), is("2"));
		tracker.findByName("InvalidName");
	}
}