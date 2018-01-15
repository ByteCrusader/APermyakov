package ru.apermyakov.servlets;

import org.junit.Test;
import ru.apermyakov.config.servlets.AddItem;
import ru.apermyakov.config.servlets.GetItems;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class for test servlets.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 15.01.2018.
 */
public class ServletsTest {

    /**
     * Test when add new item than get this item by json.
     *
     * @throws ServletException exception.
     * @throws IOException exception.
     */
    @Test
    public void whenAddNewItemThanGetThisItemByJson() throws ServletException, IOException {
        AddItem addItem = new AddItem();
        GetItems getItems = new GetItems();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("created")).thenReturn("15.01.2018.");
        when(request.getParameter("desc")).thenReturn("Check hibernate");
        when(request.getParameter("done")).thenReturn("true");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        addItem.doPost(request, response);
        getItems.doGet(request,response);

        assertThat(stringWriter.toString().contains("{\"id\":\"1\",\"created\":\"15.01.2018.\",\"description\":\"Check hibernate\",\"done\":\"true\"}"), is(true));
    }
}
