package ru.apermyakov.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonCheckIdService  extends HttpServlet {

    /**
     * Field for user store object.
     */
    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        HttpSession session = req.getSession();
        writer.append("[{\"access\":\"");
        if (("administrator").equals(session.getAttribute("role")) || (req.getParameter("id")).equals(users.getId(String.valueOf(session.getAttribute("login"))))) {
            writer.append("enable\"}]");
        } else {
            writer.append("deny\"}]");
        }
        writer.flush();
    }
}
