package ru.apermyakov.servlets;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class for test admin edit user.
 *
 * @author apermyakov
 * @version 1.0
 * @since 12.12.2017
 */
public class UserPostServletTest {

    /**
     * Method for add user.
     *
     * @throws ServletException servlet e
     * @throws IOException io e
     */
    @Test
    public void addUser() throws ServletException, IOException {

        UserPostServlet postServlet = new UserPostServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("name")).thenReturn("MockitoTest");
        when(request.getParameter("login")).thenReturn("mockitoTest");
        when(request.getParameter("email")).thenReturn("mockito@test.done");
        when(request.getParameter("password")).thenReturn("11111");
        when(request.getParameter("createDate")).thenReturn("12.12.2017");
        when(request.getParameter("role")).thenReturn("3");

        postServlet.doPost(request, response);

        assertThat(UserStore.getInstance().getUser().contains("14 MockitoTest mockitoTest"), is(true));
    }
}