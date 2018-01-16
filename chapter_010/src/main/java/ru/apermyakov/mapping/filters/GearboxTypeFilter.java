package ru.apermyakov.mapping.filters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GearboxTypeFilter extends Filter {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbFilter filter = new DbFilter();
        filter.selection(resp, String.format("from Ad ad where ad.car.gearbox.type='%s'", req.getParameter("gearboxType")));
    }
}