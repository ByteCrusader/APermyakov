package ru.apermyakov.config.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.apermyakov.config.item.Item;
import ru.apermyakov.config.service.DAO;
import ru.apermyakov.config.service.ItemDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class for modulate get servlet.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 15.01.2018.
 */
public class GetItems extends HttpServlet {

    private final DAO<Item> daoService = new ItemDAO();

    /**
     * method for do get.
     *
     * @param req req.
     * @param resp resp.
     * @throws ServletException exception.
     * @throws IOException exception.
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        try (PrintWriter writer = new PrintWriter(resp.getWriter())) {
            ObjectMapper mapper = new ObjectMapper();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            mapper.writeValue(outputStream, this.daoService.findAll());
            writer.append(new String(outputStream.toByteArray()));
            writer.flush();
        }
    }
}
