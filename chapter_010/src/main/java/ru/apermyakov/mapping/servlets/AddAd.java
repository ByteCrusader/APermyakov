package ru.apermyakov.mapping.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.apermyakov.mapping.ad.Ad;
import ru.apermyakov.mapping.car.Car;
import ru.apermyakov.mapping.car.Engine;
import ru.apermyakov.mapping.car.Gearbox;
import ru.apermyakov.mapping.car.Transmission;
import ru.apermyakov.mapping.user.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class AddAd extends HttpServlet {

    private Car initialCar(HttpServletRequest req, Engine engine, Gearbox gearbox, Transmission transmission) {
        Car car = new Car();
        car.setEngine(engine);
        car.setGearbox(gearbox);
        car.setTransmission(transmission);
        car.setMake(req.getParameter("carMake"));
        car.setModel(req.getParameter("carModel"));
        car.setYear(Integer.parseInt(req.getParameter("carYear")));
        return car;
    }

    private String downloadFile(HttpServletRequest req) throws Exception {
        DiskFileItemFactory factory = new DiskFileItemFactory();

        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        ServletFileUpload upload = new ServletFileUpload(factory);

        List<FileItem> items = upload.parseRequest(req);

        Random random = new Random();
        String result = "";

        for (FileItem item : items) {
            result = String.format("C:\\%s.PNG", random.nextInt(9999));
            item.write(new File(result));
        }

        return result;
    }

    private Ad initialAd(HttpServletRequest req, Car car, User user) throws Exception {
        Ad ad = new Ad();
        ad.setCar(car);
        ad.setDescription(req.getParameter("description"));
        ad.setUser(user);
        ad.setStatus(Boolean.parseBoolean(req.getParameter("status")));
        return ad;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try (SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> users = (List<User>) session.createQuery("from User where name=:name").setParameter("name", req.getParameter("userName")).list();
            Engine engine = session.get(Engine.class, Integer.parseInt(req.getParameter("engineId")));
            Gearbox gearbox = session.get(Gearbox.class, Integer.parseInt(req.getParameter("gearboxId")));
            Transmission transmission = session.get(Transmission.class, Integer.parseInt(req.getParameter("transmissionId")));
            Car car = initialCar(req, engine, gearbox, transmission);
            Ad ad = initialAd(req, car, users.get(0));
            session.save(car);
            session.save(ad);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
