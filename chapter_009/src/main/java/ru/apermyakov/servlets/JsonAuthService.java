package ru.apermyakov.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonAuthService extends HttpServlet {

    /**
     * Field for enable sessions.
     */
    private final Sessions sessions = Sessions.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("[{\"sessionCondition\":\"");
        HttpSession session = req.getSession();
        String condition = "disable\"}]";
        for (Cookie cookie : req.getCookies()) {
            if (("JSESSIONID").equals(cookie.getName())) {
                if (session.isNew()) {
                    cookie.setValue(session.getId());
                }
                if (this.sessions.isContainCookie(cookie.getValue())) {
                    condition = "enable\"}]";
                }
            }
        }
        writer.append(condition);
        writer.flush();
    }
}
