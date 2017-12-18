package ru.apermyakov.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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
        for (Map.Entry<Integer, HashMap<String, String>> userEntry : users.getUsersMap().entrySet()) {
            if (record) {
                writer.append(",");
            }
            writer.append("{\"id\":\"");
            writer.append(String.valueOf(userEntry.getKey()));
            writer.append("\"");
            for (Map.Entry<String, String> user : userEntry.getValue().entrySet()) {
                    writer.append(",");
                writer.append("\"");
                writer.append(user.getKey());
                writer.append("\":\"");
                writer.append(user.getValue());
                writer.append("\"");
            }
            writer.append("}");
            record = true;
        }
        writer.append("]");
        writer.flush();
    }
}
