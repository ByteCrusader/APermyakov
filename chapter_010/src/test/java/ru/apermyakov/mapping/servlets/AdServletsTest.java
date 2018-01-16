package ru.apermyakov.mapping.servlets;

import org.junit.Test;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Base64;
import java.util.Enumeration;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdServletsTest {

    @Test
    public void whenThen() throws ServletException, IOException {
        AddAd adServlet = new AddAd();
        GetAllAds getAllAds = new GetAllAds();
        AddUser addUser = new AddUser();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

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
                return "Ivan";
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

        when(request.getHeader("Authorization")).thenReturn("Basic " + new String(Base64.getEncoder().encode("check@mail.ru : ***".getBytes())));
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("carMake")).thenReturn("Toyota");
        when(request.getParameter("carModel")).thenReturn("Mark II");
        when(request.getParameter("carYear")).thenReturn("2000");
        when(request.getParameter("description")).thenReturn("Sell mark II");
        when(request.getParameter("status")).thenReturn("true");
        when(request.getParameter("userName")).thenReturn("Ivan");
        when(request.getParameter("engineId")).thenReturn("1");
        when(request.getParameter("gearboxId")).thenReturn("1");
        when(request.getParameter("transmissionId")).thenReturn("1");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        addUser.doPost(request, response);
        adServlet.doPost(request, response);
        getAllAds.doGet(request, response);

        assertThat(stringWriter.toString().contains("{\"id\":\"1\",\"description\":\"Sell mark II\",\"car\":\"Toyota Mark II\",\"user\":\"Ivan\",\"photo\":\"\",\"status\":\"true\"}"), is(true));
    }
}