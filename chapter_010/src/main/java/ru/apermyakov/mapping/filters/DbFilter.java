package ru.apermyakov.mapping.filters;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.apermyakov.mapping.ad.Ad;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DbFilter {

    public void selection(HttpServletResponse resp, String hqlQuery) {
        resp.setContentType("text/json");
        boolean record = false;

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        try (SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
             Session session = sessionFactory.openSession();
             PrintWriter writer = new PrintWriter(resp.getWriter())) {

            session.beginTransaction();
            List<Ad> result = (List<Ad>) session.createQuery(hqlQuery).list();
            writer.append("[");
            for (Ad ad : result) {
                if (record) {
                    writer.append(",");
                }
                writer.append("{\"id\":\"");
                writer.append(String.valueOf(ad.getId()));
                writer.append("\",\"description\":\"");
                writer.append(ad.getDescription());
                writer.append("\",\"car\":\"");
                writer.append(ad.getCar().getMake());
                writer.append(" ");
                writer.append(ad.getCar().getModel());
                writer.append("\",\"user\":\"");
                writer.append(ad.getUser().getName());
                writer.append("\",\"photo\":\"");
                writer.append("");
                writer.append("\",\"status\":\"");
                writer.append(String.valueOf(ad.isStatus()));
                writer.append("\"}");
                record = true;
            }
            writer.append("]");
            writer.flush();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
