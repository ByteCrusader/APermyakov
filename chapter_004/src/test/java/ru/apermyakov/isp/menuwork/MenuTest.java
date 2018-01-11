package ru.apermyakov.isp.menuwork;

import org.junit.Before;
import org.junit.Test;
import ru.apermyakov.isp.menuitems.Item;
import ru.apermyakov.isp.menuitems.MenuItem;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for modulate menu test.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 10.01.2018.
 */
public class MenuTest {

    /**
     * Field for menu.
     */
    private Menu menu = new MenuAct();

    /**
     * Field ofr console output stream.
     */
    private ByteArrayOutputStream console = new ByteArrayOutputStream();

    /**
     * Method for initial all fields before tests.
     */
    @Before
    public void initial() {
        Item firstItem = new MenuItem("1.", "First");
        Item secondItem = new MenuItem("1.1.", "Second");
        Item thirdItem = new MenuItem("1.1.1.", "Third");
        Item fourthItem = new MenuItem("1.2.", "Fourth");
        Item fifthItem = new MenuItem("2.", "Fifth");

        this.menu.addToMenuList(thirdItem);
        this.menu.addToMenuList(fifthItem);
        this.menu.addToMenuList(firstItem);
        this.menu.addToMenuList(secondItem);
        this.menu.addToMenuList(fourthItem);

        System.setOut(new PrintStream(console));
    }

    /**
     * Test when show menu then console end with last menu's item.
     */
    @Test
    public void whenShowMenuThenConsoleEndWithLastMenuItem() {
        this.menu.showMenu();
        assertThat(console.toString().endsWith(" 2. Fifth \r\n"), is(true));
    }

    /**
     * Test when show and work with third item then console end with message about third item's action condition.
     */
    @Test
    public void whenShowAndWorkWithThirdItemThenConsoleEndWithMessageAboutThirdItemActionCondition() {
        this.menu.showMenu();
        this.menu.workItem("1.1.1.");
        assertThat(console.toString().endsWith("The action of the 1.1.1. Third menu item is executed\r\n"), is(true));
    }
}