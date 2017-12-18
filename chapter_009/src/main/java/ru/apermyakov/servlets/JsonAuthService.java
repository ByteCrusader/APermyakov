package ru.apermyakov.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonAuthService extends HttpServlet {

    /**
     * Field for user store object.
     */
    private final UserStore users = UserStore.getInstance();

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
                if (users.sessions.containsKey(cookie.getValue())) {
                    condition = "enable\"}]";
                }
            }
        }
        writer.append(condition);
        writer.flush();
    }
}
