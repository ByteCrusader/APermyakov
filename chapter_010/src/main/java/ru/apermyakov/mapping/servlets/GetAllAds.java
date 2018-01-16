package ru.apermyakov.mapping.servlets;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.apermyakov.mapping.ad.Ad;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetAllAds extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        boolean record = false;

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        try (SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
             Session session = sessionFactory.openSession();
             PrintWriter writer = new PrintWriter(resp.getWriter())) {

            session.beginTransaction();
            List<Ad> result = (List<Ad>) session.createQuery( "from Ad").list();
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
        }
    }
}
