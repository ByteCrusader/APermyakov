package ru.apermyakov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class for test run application with auto info.
*
* @author apermyakov
* @since 16.10.2017
* @version 1.0
*/

public class StubInputTest {

	/**
	* Test when user add new item then tracker has this item.
	*/
	@Test
	public void whenUserAddItemThenTrackerHasThisItem() {
		Tracker tracker = new Tracker();
		Input input = new StubInput(new String[] {"0", "ivan", "check", "6"});
		new StartUI(input, tracker).initial();
		assertThat(tracker.findAll().get(0).getName(), is("ivan"));
	}

	/**
	* Test when user add new three item and chouse show then tracker has this three item and user see this items.
	*/
	@Test
	public void whenUserAddThreeItemAndChouseShowThenTrackerHasThreeItemsAndUserSeeThreeItems() {
		Tracker tracker = new Tracker();
		Item first = tracker.add(new Item("vasilii", "check aircondition", 123L));
		Item second = tracker.add(new Item("ivan", "need to repair TV", 123L));
		Item third = tracker.add(new Item("vasilii", "repair TV", 123L));
		Input input = new StubInput(new String[] {"1", "6"});
		new StartUI(input, tracker).initial();
		assertThat(tracker.findAll().size(), is(3));
	}

	/**
	* Test when user edit item then tracker has update this item.
	*/
	@Test
	public void whenUserEditItemThenTrackerHasUpdatedValue() {
		Tracker tracker = new Tracker();
		Item item = tracker.add(new Item("ivan", "repair TV", 123L));
		Input input = new StubInput(new String[]{"2", item.getId(), "vasilii", "check aircondition", "6"});
		new StartUI(input, tracker).initial();
		assertThat(tracker.findById(item.getId()).getName(), is("vasilii"));
	}

	/**
	* Test when user delete first item then tracker has second item on the place of first.
	*/
	@Test
	public void whenUserDeleteFirstItemThenTrackerHasSecondItemOnTheFirstItem() {
		Tracker tracker = new Tracker();
		Item first = tracker.add(new Item("ivan", "cry", 123L));
		Item second = tracker.add(new Item("vasilii", "comfort", 123L));
		Input input = new StubInput(new String[]{"3", first.getId(), "6"});
		new StartUI(input, tracker).initial();
		assertThat(tracker.findAll().get(0).getName(), is("vasilii"));
	}

	/**
	* Test when user afind by id item then tracker has this item.
	*/
	@Test
	public void whenUserFindByIdThenTrackerHasThisItem() {
		Tracker tracker = new Tracker();
		Item item = tracker.add(new Item("ivan", "cry", 123L));
		Input input = new StubInput(new String[]{"4", item.getId(), "6"});
		new StartUI(input, tracker).initial();
		assertThat(tracker.findById(item.getId()).getDescription(), is("cry"));
	}

	/**
	* Test when user find by name item then tracker has array of items with this name.
	*/
	@Test
	public void whenUserFindByNameIvanThenTrackerHasThisThreeItems() {
		Tracker tracker = new Tracker();
		Item first = tracker.add(new Item("ivan", "need to repair TV", 123L));
		Item second = tracker.add(new Item("ivan", "cry", 123L));
		Item third = tracker.add(new Item("vasilii", "repair TV", 123L));
		Item fourth = tracker.add(new Item("ivan", "cheerful", 123L));
		Input input = new StubInput(new String[]{"5", fourth.getName(), "6"});
		new StartUI(input, tracker).initial();
		assertThat(tracker.findByName(fourth.getName()).size(), is(3));
	}
}
