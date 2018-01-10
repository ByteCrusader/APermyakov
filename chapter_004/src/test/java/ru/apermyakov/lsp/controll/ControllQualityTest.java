package ru.apermyakov.lsp.controll;

import org.junit.Before;
import org.junit.Test;
import ru.apermyakov.lsp.food.*;
import ru.apermyakov.lsp.storage.Shop;
import ru.apermyakov.lsp.storage.Storage;
import ru.apermyakov.lsp.storage.Trash;
import ru.apermyakov.lsp.storage.Warehouse;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test quality controller.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 10.01.2018.
 */
public class ControllQualityTest {

    /**
     * Field for product food.
     */
    private Products food = new Food();

    /**
     * Field for product chicken.
     */
    private Products chicken = new Chicken();

    /**
     * Field for product cheese.
     */
    private Products cheese = new Cheese();

    /**
     * Field for product buckwheat.
     */
    private Products buckwheat = new Buckwheat();

    /**
     * Field for storage warehouse.
     */
    private Storage warehouse = new Warehouse();

    /**
     * Field for storage trash.
     */
    private Storage trash = new Trash();

    /**
     * Field for storage chop.
     */
    private Storage shop = new Shop();

    /**
     * Field for controller.
     */
    private Controller controll = new ControllQuality();

    /**
     * Method for initial all needed field before tests.
     */
    @Before
    public void initial() {
        this.food.setName("bun");
        this.food.setPrice(15);
        Calendar foodCreateDate = new GregorianCalendar(2018, Calendar.JANUARY, 8, 10, 20);
        Calendar foodExpireDate = new GregorianCalendar(2018, Calendar.JANUARY, 12, 10, 20);
        this.food.setCreateDate(foodCreateDate);
        this.food.setExpireDate(foodExpireDate);
        this.food.setDiscount(5);

        Calendar chickenCreateDate = new GregorianCalendar(2018, Calendar.JANUARY, 8, 10, 20);
        Calendar chickenExpireDate = new GregorianCalendar(2018, Calendar.JANUARY, 10, 15, 20);
        this.chicken.setPrice(120);
        this.chicken.setCreateDate(chickenCreateDate);
        this.chicken.setExpireDate(chickenExpireDate);
        this.chicken.setDiscount(20);

        Calendar cheeseCreateDate = new GregorianCalendar(2018, Calendar.JANUARY, 10, 10, 20);
        Calendar cheeseExpireDate = new GregorianCalendar(2018, Calendar.JANUARY, 18, 10, 20);
        this.cheese.setPrice(400);
        this.cheese.setCreateDate(cheeseCreateDate);
        this.cheese.setExpireDate(cheeseExpireDate);

        Calendar buckwheatCreateDate = new GregorianCalendar(2018, Calendar.JANUARY, 5, 10, 20);
        Calendar buckwheatExpireDate = new GregorianCalendar(2018, Calendar.JANUARY, 8, 10, 20);
        this.buckwheat.setPrice(50);
        this.buckwheat.setCreateDate(buckwheatCreateDate);
        this.buckwheat.setExpireDate(buckwheatExpireDate);

        this.controll.addStorageToList(warehouse);
        this.controll.addStorageToList(trash);
        this.controll.addStorageToList(shop);
    }

    /**
     * Test when add food with 50 percent then take storage name shop.
     */
    @Test
    public void whenAddFoodWith50PercentThenTakeStorageNameShop() {
        assertThat(this.controll.distributeProduct(this.food), is("Shop"));
    }

    /**
     * Test when add chicken with 90 percent then take price with discount.
     */
    @Test
    public void whenAddChickenWith90PercentThenTakePriceWithDiscount() {
        this.controll.distributeProduct(this.chicken);
        assertThat(chicken.getPrice(), is(100));
    }

    /**
     * Test when add cheese with 10 percent then take storage name warehouse.
     */
    @Test
    public void whenAddCheeseWith10PercentThenTakeStorageNameWarehouse() {
        assertThat(this.controll.distributeProduct(this.cheese), is("Warehouse"));
    }

    /**
     * Test when add buckwheat with more than 100 percent then take storage name trash.
     */
    @Test
    public void whenAddBuckwheatWithMoreThan100PercentThenTakeStorageNameTrash() {
        assertThat(this.controll.distributeProduct(this.buckwheat), is("Trash"));
    }

    /**
     * Test when get all storages than contain shop storage.
     */
    @Test
    public void whenGetAllStoragesThanContainShopStorage() {
        assertThat(this.controll.getAllStorages().get(2).getName(), is(this.shop.getName()));
    }

    /**
     * Test when get all products from trash than contain buckwheat.
     */
    @Test
    public void whenGetAllProductsFromTrashThanContainBuckwheat() {
        this.controll.distributeProduct(this.buckwheat);
        assertThat(this.controll.getStorageProducts(this.trash.getName()).get(0).getName(), is(this.buckwheat.getName()));
    }
}