package ru.apermyakov.ioc.picker;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.apermyakov.ioc.storage.MemoryStorage;
import ru.apermyakov.ioc.storage.Storage;
import ru.apermyakov.ioc.user.SimpleUser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test picker.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
public class PickerTest {

    /**
     * Field for console.
     */
    private ByteArrayOutputStream console = new ByteArrayOutputStream();

    /**
     * Initial console listener.
     */
    @Before
    public void initial() {
        System.setOut(new PrintStream(console));
    }

    /**
     * Test when add user using xml simple bean than correct add to memory.
     */
    @Test
    public void whenAddUserUsingXMLSimpleBeanThanCorrectAddToMemory() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Storage storage = context.getBean(MemoryStorage.class);
        Picker picker = new UserPicker(storage);
        picker.add(new SimpleUser());

        assertThat(console.toString().endsWith("Add to memory\r\n"), is(true));
    }

    /**
     * Test when add user using xml bean with dependency than correct add to memory.
     */
    @Test
    public void whenAddUserUsingXMLBeanWithDependencyThanCorrectAddToMemory() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Picker picker = context.getBean(UserPicker.class);
        picker.add(new SimpleUser());

        assertThat(console.toString().endsWith("Add to memory\r\n"), is(true));
    }

    /**
     * Test when add user using annotation than correct add to db.
     */
    @Test
    public void whenAddUserUsingAnnotationThanCorrectAddToDb() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Picker picker = context.getBean(AdminPicker.class);
        picker.add(new SimpleUser());

        assertThat(console.toString().endsWith("Add to DB\r\n"), is(true));
    }
}