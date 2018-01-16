package ru.apermyakov.mapping.filters;

import org.junit.Test;

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

public class GearboxTypeFilterTest {

    @Test
    public void whenGearboxTypeAutomaticThanMarkII() throws IOException, ServletException {
        Filter filter = new GearboxTypeFilter();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("gearboxType")).thenReturn("automatic");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        filter.doGet(request, response);

        assertThat(stringWriter.toString().contains("{\"id\":\"2\",\"description\":\"Sell mark II\",\"car\":\"Toyota Mark II\",\"user\":\"Ivan\",\"photo\":\"\",\"status\":\"true\"}"), is(true));
    }
}