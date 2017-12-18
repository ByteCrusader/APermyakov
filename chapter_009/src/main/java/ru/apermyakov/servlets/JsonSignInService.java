package ru.apermyakov.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

public class JsonSignInService extends HttpServlet {

    /**
     * Field for user store object.
     */
    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        String logPas = req.getHeader("Authorization");
        String res = new String(Base64.getDecoder().decode(logPas.replace("Basic ", "")));
        String[] authPare = res.split(":");

        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("[{\"access\":\"");
        if (users.isPastInspection(authPare[0], authPare[1])) {
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("login", authPare[0]);
                session.setAttribute("role", users.getRole(authPare[0]));
            }
            for (Cookie cookie : req.getCookies()) {
                if (("JSESSIONID").equals(cookie.getName())) {
                    users.sessions.put(cookie.getValue(), session);
                        writer.append("allow\"}]");
                        break;
                }
            }
        } else {
            writer.append("deny\"}]");
        }
        writer.flush();
    }
}
