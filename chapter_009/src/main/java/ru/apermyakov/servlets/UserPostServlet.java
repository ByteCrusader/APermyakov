package ru.apermyakov.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Servlet to show to user post interface.
 *
 * @author apermyakov
 * @version 1.0
 * @since 07.12.2017
 */
public class UserPostServlet extends HttpServlet {

    /**
     * Field for user store object.
     */
    private final UserStore users = UserStore.getInstance();

    /**
     * Method for work with get request.
     *
     * @param req request
     * @param resp response
     * @throws ServletException servlet e
     * @throws IOException io e
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        writer.append("<!DOCTYPE html>");
        writer.append("<html lang=\"en\">");
        writer.append("<head>");
        writer.append("   <meta charset=\"UTF-8\">");
        writer.append("   <title></title>");
        writer.append("</head>");
        writer.append("<body>");
        writer.append("<br/>");
        writer.append("<form action='");
        writer.append(req.getContextPath());
        writer.append("/user/post?createDate=");
        writer.append(dateFormat.format(Calendar.getInstance().getTime()));
        writer.append("&' method='post'>");
        writer.append("   Insert new user:");
        writer.append("<br/>");
        writer.append("   Name : <input type='text' name='name'/>");
        writer.append("<br/>");
        writer.append("   Login : <input type='text' name='login'/>");
        writer.append("<br/>");
        writer.append( "   Email : <input type='text' name='email'/>");
        writer.append("<br/>");
        writer.append("   <input type='submit' value='Add new user'>");
        writer.append("</form>");
        writer.append("</body>");
        writer.append("</html>");
        writer.flush();
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
        users.post(req);
        resp.sendRedirect(req.getContextPath() + "/interface");
    }
}
