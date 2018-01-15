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
import java.io.PrintWriter;
import java.util.List;

/**
 * Class for modulate get servlet.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 15.01.2018.
 */
public class GetItems extends HttpServlet {

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
        boolean record = false;

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        try (SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
             Session session = sessionFactory.openSession();
             PrintWriter writer = new PrintWriter(resp.getWriter())) {

            session.beginTransaction();
            List<Item> result = (List<Item>) session.createQuery( "from Item").list();
            writer.append("[");
            for (Item item : result) {
                if (record) {
                    writer.append(",");
                }
                writer.append("{\"id\":\"");
                writer.append(String.valueOf(item.getId()));
                writer.append("\",\"created\":\"");
                writer.append(item.getCreated());
                writer.append("\",\"description\":\"");
                writer.append(item.getDescription());
                writer.append("\",\"done\":\"");
                writer.append(String.valueOf(item.isDone()));
                writer.append("\"}");
                record = true;
            }
            writer.append("]");
            writer.flush();
            session.getTransaction().commit();
        }
    }
}
