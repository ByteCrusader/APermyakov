package ru.apermyakov.servlets.testtask;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

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
        DAOFactory factory = new DAOFactory();
        try (Connection connection = factory.getConnection()) {
            UserDAO userDAO = factory.getUserDAO(connection);
            ObjectMapper mapper = new ObjectMapper();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            mapper.writeValue(outputStream,userDAO.choseAll());
            writer.append(new String(outputStream.toByteArray()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        writer.append("]");
        writer.flush();
    }
}
