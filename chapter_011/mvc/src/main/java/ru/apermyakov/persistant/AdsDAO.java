package ru.apermyakov.persistant;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.apermyakov.model.ad.Ad;
import ru.apermyakov.model.car.Car;
import ru.apermyakov.model.car.Engine;
import ru.apermyakov.model.car.Gearbox;
import ru.apermyakov.model.car.Transmission;
import ru.apermyakov.model.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Ads dao object.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 18.01.2018.
 */
public class AdsDAO {

    /**
     * Method for build session factory.
     *
     * @return session factory.
     */
    private SessionFactory sessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    /**
     * Method for add ad.
     *
     * @param userId user id.
     * @param ad partially filled ad.
     * @param car partially filled car.
     * @param engineId engine id.
     * @param gearboxId gearbox id.
     * @param transmissionId transmission id.
     */
    public void addAd(int userId, Ad ad, Car car, int engineId, int gearboxId, int transmissionId) {
        try (Session session = this.sessionFactory().openSession()) {
            session.beginTransaction();
            car.setEngine(new Engine(engineId));
            car.setGearbox(new Gearbox(gearboxId));
            car.setTransmission(new Transmission(transmissionId));
            ad.setUser(new User(userId));
            ad.setCar(car);
            session.save(car);
            session.save(ad);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for get all ads.
     *
     * @return list of ads.
     */
    public List<Ad> getAdsList() {
        List<Ad> result = new ArrayList<>();
        try (Session session = this.sessionFactory().openSession()) {
            session.beginTransaction();
            result = (List<Ad>) session.createQuery( "from Ad").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
