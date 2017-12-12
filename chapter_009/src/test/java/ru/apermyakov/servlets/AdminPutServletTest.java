package ru.apermyakov.servlets;

import org.junit.Test;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import java.io.IOException;
import java.util.Enumeration;

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
public class AdminPutServletTest {

    /**
     * Test admin edit user.
     *
     * @throws ServletException servlet e
     * @throws IOException io e
     */
    @Test
    public void adminEditUser() throws ServletException, IOException {

        AdminPutServlet putServlet = new AdminPutServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("name")).thenReturn("MockitoTestAdmin");
        when(request.getParameter("login")).thenReturn("mockitoTestAdmin");
        when(request.getParameter("email")).thenReturn("mockitoAdmin@test.done");
        when(request.getParameter("password")).thenReturn("11111");
        when(request.getParameter("role")).thenReturn("3");
        when(request.getParameter("id")).thenReturn("7");

        HttpSession session = new HttpSession() {
            @Override
            public long getCreationTime() {
                return 0;
            }

            @Override
            public String getId() {
                return null;
            }

            @Override
            public long getLastAccessedTime() {
                return 0;
            }

            @Override
            public ServletContext getServletContext() {
                return null;
            }

            @Override
            public void setMaxInactiveInterval(int i) {

            }

            @Override
            public int getMaxInactiveInterval() {
                return 0;
            }

            @Override
            public HttpSessionContext getSessionContext() {
                return null;
            }

            @Override
            public Object getAttribute(String s) {
                return "admin";
            }

            @Override
            public Object getValue(String s) {
                return null;
            }

            @Override
            public Enumeration<String> getAttributeNames() {
                return null;
            }

            @Override
            public String[] getValueNames() {
                return new String[0];
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public void putValue(String s, Object o) {

            }

            @Override
            public void removeAttribute(String s) {

            }

            @Override
            public void removeValue(String s) {

            }

            @Override
            public void invalidate() {

            }

            @Override
            public boolean isNew() {
                return false;
            }
        };

        when(request.getSession()).thenReturn(session);

        putServlet.doPost(request, response);

        assertThat(UserStore.getInstance().getUser().contains("7 MockitoTestAdmin mockitoTestAdmin mockitoAdmin@test.done 12.12.2017 student"), is(true));
    }
}