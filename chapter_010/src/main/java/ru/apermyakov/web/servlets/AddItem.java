package ru.apermyakov.config.servlets;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.apermyakov.config.item.Item;

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
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try (SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Item item = new Item();
            item.setId(Integer.parseInt(req.getParameter("id")));
            item.setCreated(req.getParameter("created"));
            item.setDescription(req.getParameter("desc"));
            item.setDone(Boolean.parseBoolean(req.getParameter("done")));
            session.save(item);
            session.getTransaction().commit();
        }
    }
}
