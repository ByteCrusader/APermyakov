package ru.apermyakov.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet for sing in.
 *
 * @author apermyakov
 * @version 1.0
 * @since 08.12.2017
 */
public class SignInServlet extends HttpServlet {

    /**
     * Field for user store object.
     */
    private final UserStore users = UserStore.getInstance();

    /**
     * Method for work with getUser request.
     *
     * @param req request
     * @param resp response
     * @throws ServletException servlet e
     * @throws IOException io e
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/Authentication.jsp").forward(req, resp);
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
       if (users.isPastInspection(req)) {
           HttpSession session = req.getSession();
           synchronized (session) {
               session.setAttribute("login", req.getParameter("login"));
           }
           resp.sendRedirect(req.getContextPath() + "/");
       } else {
           req.setAttribute("greeting", "Invalid login or password, try again");
           doGet(req, resp);
       }
    }
}
