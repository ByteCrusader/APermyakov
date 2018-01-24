package ru.apermyakov.config.servlets;

import ru.apermyakov.config.item.Item;
import ru.apermyakov.config.service.DAO;
import ru.apermyakov.config.service.ItemDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class for modulate add servlet.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 15.01.2018.
 */
public class AddItem extends HttpServlet {

    private final DAO<Item> daoService = new ItemDAO();

    /**
     * Method for do post.
     *
     * @param req req.
     * @param resp resp.
     * @throws ServletException exception.
     * @throws IOException exception.
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.daoService.create(new Item(Integer.parseInt(req.getParameter("id"))
                                            , req.getParameter("desc")
                                            , req.getParameter("created")
                                            , Boolean.parseBoolean(req.getParameter("done"))));
    }
}
