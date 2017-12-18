package ru.apermyakov.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PutService extends HttpServlet {

    /**
     * Field for user store object.
     */
    private final UserStore users = UserStore.getInstance();

    /**
     * Method for work with post request.
     *
     * @param req request
     * @param resp response
     * @throws ServletException servlet e
     * @throws IOException io e
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        boolean post = true;
        writer.append("[{\"correct\":\"");
        if (req.getParameter("name").toLowerCase().equals(req.getParameter("name"))) {
            writer.append("no\"},{\"mistake\":\"name\"}");
            post = false;
        }
        if (post
                && !(req.getParameter("email").contains("@")
                && req.getParameter("email").contains("."))) {
            writer.append("no\"},{\"mistake\":\"email\"}");
            post = false;
        }
        if (post) {
            writer.append("yes\"}");
            users.put(req);
        }
        writer.append("]");
        writer.flush();
    }
}