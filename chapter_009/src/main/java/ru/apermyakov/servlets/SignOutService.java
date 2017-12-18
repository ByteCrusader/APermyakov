package ru.apermyakov.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SignOutService extends HttpServlet {

    /**
     * Field for user store object.
     */
    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        synchronized (session) {
            session.removeAttribute("login");
            session.removeAttribute("role");
        }
        for (Cookie cookie : req.getCookies()) {
            if (("JSESSIONID").equals(cookie.getName())) {
                users.sessions.remove(cookie.getValue());
            }
        }
    }
}
