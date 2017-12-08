package ru.apermyakov.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to show to user interface.
 *
 * @author apermyakov
 * @version 1.0
 * @since 07.12.2017
 */
public class InterfaceServlet extends HttpServlet{

    /**
     * Field for user store object.
     */
    private final UserStore users = UserStore.getInstance();

    /**
     * Method to work with get request.
     *
     * @param req request
     * @param resp response
     * @throws ServletException servlet e
     * @throws IOException io e
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", users.get());
        req.getRequestDispatcher("/WEB-INF/views/Interface.jsp").forward(req, resp);
    }

    /**
     * Method for close connect to DB when servlet end.
     */
    @Override
    public void destroy() {
        users.closeConnect();
    }
}
