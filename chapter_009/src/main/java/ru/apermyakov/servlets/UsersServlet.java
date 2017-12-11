package ru.apermyakov.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class for users servlet.
 *
 * @author apermyakov
 * @version 1.0
 * @since 06.12.2017
 */
public class UsersServlet extends HttpServlet {

    /**
     * Field for user store object.
     */
    private final UserStore users = UserStore.getInstance();

    /**
     * Method to work with getUser request.
     *
     * @param req http request
     * @param resp http response
     * @throws ServletException servlet e
     * @throws IOException io e
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>");
        writer.append("<html lang=\"en\">");
        writer.append("<head>");
        writer.append("   <meta charset=\"UTF-8\">");
        writer.append("   <title></title>");
        writer.append("</head>");
        writer.append("<body>");
        writer.append(users.getUser());
        writer.append("</body>");
        writer.append("</html>");
        writer.flush();
    }

    /**
     * Method to work with post request.
     *
     * @param req http request
     * @param resp http response
     * @throws ServletException servlet e
     * @throws IOException io e
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        users.post(req);
    }

    /**
     * Method for work with put request.
     *
     * @param req http request
     * @param resp http response
     * @throws ServletException servlet e
     * @throws IOException io e
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        users.put(req);
    }

    /**
     * Method for work with delete request.
     *
     * @param req http request
     * @param resp http response
     * @throws ServletException servlet e
     * @throws IOException io e
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        users.delete(req);
    }
}
