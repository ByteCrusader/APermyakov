package ru.apermyakov.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SignOutService extends HttpServlet {

    /**
     * Field for enable sessions.
     */
    private final Sessions sessions = Sessions.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("login");
        session.removeAttribute("role");
        for (Cookie cookie : req.getCookies()) {
            if (("JSESSIONID").equals(cookie.getName())) {
                this.sessions.deleteSession(cookie.getValue());
            }
        }
    }
}
