package ru.apermyakov.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class JsonCityService extends HttpServlet {

    /**
     * Field for user store object.
     */
    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        boolean record = false;
        writer.append("[");
        for (Map.Entry<Integer, String> roleEntry : users.getCitiesByCountry(req.getParameter("country")).entrySet()) {
            if (record) {
                writer.append(",");
            }
            writer.append("{\"id\":\"");
            writer.append(String.valueOf(roleEntry.getKey()));
            writer.append("\",\"country\":\"");
            writer.append(roleEntry.getValue());
            writer.append("\"");
            writer.append("}");
            record = true;
        }
        writer.append("]");
        writer.flush();
    }
}
