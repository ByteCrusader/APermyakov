package ru.apermyakov.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Service for initial check user id and return json result
 *
 * @author apermyakov
 * @version 1.0
 * @since 15.12.2017
 */
public class JsonCheckIdService  extends HttpServlet {

    /**
     * Field for user store object.
     */
    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        HttpSession session = req.getSession();
        user.setLogin(String.valueOf(session.getAttribute("login")));
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("[{\"access\":\"");
        if (("administrator").equals(session.getAttribute("role")) || (req.getParameter("id")).equals(users.getId(user))) {
            writer.append("enable\"}]");
        } else {
            writer.append("deny\"}]");
        }
        writer.flush();
    }
}
