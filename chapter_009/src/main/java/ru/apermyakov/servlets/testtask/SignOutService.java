package ru.apermyakov.servlets.testtask;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Class for work with sign out action.
 *
 * @author apermyakov
 * @version 1.0
 * @since 21.12.2017
 */
public class SignOutService extends HttpServlet {

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
    }
}
