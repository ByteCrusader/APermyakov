package ru.apermyakov.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonInterfaceService extends HttpServlet {

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
        for (TransferObject user : users.getUsers()) {
            if (record) {
                writer.append(",");
            }
            writer.append("{\"id\":\"");
            writer.append(String.valueOf(user.getId()));
            writer.append("\",\"name\":\"");
            writer.append(user.getName());
            writer.append("\",\"login\":\"");
            writer.append(user.getLogin());
            writer.append("\",\"email\":\"");
            writer.append(user.getEmail());
            writer.append("\",\"createDate\":\"");
            writer.append(user.getCreateDate());
            writer.append("\",\"role\":\"");
            writer.append(user.getRole());
            writer.append("\",\"country\":\"");
            writer.append(user.getCountry());
            writer.append("\",\"city\":\"");
            writer.append(user.getCity());
            writer.append("\"}");
            record = true;
        }
        writer.append("]");
        writer.flush();
    }
}
