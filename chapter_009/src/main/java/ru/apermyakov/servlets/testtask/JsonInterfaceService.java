package ru.apermyakov.servlets.testtask;

import ru.apermyakov.servlets.TransferObject;
import ru.apermyakov.servlets.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Class for modulate interface json.
 *
 * @author apermyakov
 * @version 1.0
 * @since 21.12.2017
 */
public class JsonInterfaceService extends HttpServlet {

    /**
     * Method for build json for interface table.
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
        boolean record = false;
        writer.append("[");

        DAOFactory factory = new DAOFactory();
        try (Connection connection = factory.getConnection()) {
            UserDAO userDAO = factory.getUserDAO(connection);
            List<TransferObject> users = userDAO.choseAll();
            for (TransferObject user : users) {
                if (record) {
                    writer.append(",");
                }
                writer.append("{\"id\":\"");
                writer.append(String.valueOf(user.getId()));
                writer.append("\",\"name\":\"");
                writer.append(user.getName());
                writer.append("\",\"role\":\"");
                writer.append(user.getRole());
                writer.append("\",\"address\":\"");
                writer.append(user.getAddress());
                writer.append("\",\"musictype\":[");
                boolean musicRecord = false;
                for (String musicType : user.getMusicType()) {
                    if (musicRecord) {
                        writer.append(",");
                    }
                    writer.append("\"");
                    writer.append(musicType);
                    writer.append("\"");
                    musicRecord = true;
                }
                writer.append("]}");
                record = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        writer.append("]");
        writer.flush();
    }
}
