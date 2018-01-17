package ru.apermyakov.ioc.work;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.apermyakov.ioc.user.SimpleUser;
import ru.apermyakov.ioc.user.User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for test import user.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
public class ImportTest {

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
     * Test when add user to db than take message about it.
     */
    @Test
    public void whenAddUserToDbThanTakeMessageAboutIt(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Import userImport = context.getBean(UserImport.class);
        User user = new SimpleUser();
        user.setName("Ivan");
        userImport.importUser(user);
        assertThat(console.toString().endsWith("Add Ivan to DB\r\n"), is(true));
    }
}