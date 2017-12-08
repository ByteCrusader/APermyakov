package ru.apermyakov.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Servlet to show to user post interface.
 *
 * @author apermyakov
 * @version 1.0
 * @since 07.12.2017
 */
public class UserPostServlet extends HttpServlet {

    /**
     * Field for user store object.
     */
    private final UserStore users = UserStore.getInstance();

    /**
     * Method for work with get request.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("dateFormat", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"));
        req.setAttribute("calendar", Calendar.getInstance().getTime());
        req.getRequestDispatcher("/WEB-INF/views/PostUser.jsp").forward(req, resp);
    }

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
        users.post(req);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
