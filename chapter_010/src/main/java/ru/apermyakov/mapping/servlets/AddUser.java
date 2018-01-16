package ru.apermyakov.mapping.servlets;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.apermyakov.mapping.user.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

public class AddUser extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try (SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String logPas = req.getHeader("Authorization");
            String res = new String(Base64.getDecoder().decode(logPas.replace("Basic ", "")));
            String[] authPare = res.split(":");
            User user = new User();
            user.setName(req.getSession().getAttribute("userName").toString());
            user.setEmail(authPare[0]);
            user.setPassword(authPare[1]);
            session.save(user);
            session.getTransaction().commit();
        }
    }
}
