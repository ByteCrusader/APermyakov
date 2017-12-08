package ru.apermyakov.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to show to user delete interface.
 *
 * @author apermyakov
 * @version 1.0
 * @since 07.12.2017
 */
public class UserDeleteServlet extends HttpServlet {

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
        req.getRequestDispatcher("/WEB-INF/views/DeleteUser.jsp").forward(req, resp);
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
        users.delete(req);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
