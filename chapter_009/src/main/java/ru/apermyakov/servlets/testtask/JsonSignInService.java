package ru.apermyakov.servlets.testtask;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class for work with auth.
 *
 * @author apermyakov
 * @version 1.0
 * @since 21.12.2017
 */
public class JsonSignInService extends HttpServlet {

    /**
     * Method for check auth opportunity.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");

        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("[{\"access\":\"");

        RoleRepository repository = new RoleRepository();
        RoleRelatedEntitiesSpecification specification = new RoleRelatedEntitiesSpecification(req.getParameter("role").toUpperCase());

        if (!repository.query(specification).isEmpty()) {
            HttpSession session = req.getSession();
            session.setAttribute("role", req.getParameter("role").toUpperCase());
            for (Cookie cookie : req.getCookies()) {
                if (("JSESSIONID").equals(cookie.getName())) {
                    if (session.isNew()) {
                        cookie.setValue(session.getId());
                    }
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
