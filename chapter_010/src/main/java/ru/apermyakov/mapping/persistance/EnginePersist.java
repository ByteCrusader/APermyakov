package ru.apermyakov.mapping.persistance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.apermyakov.mapping.car.Engine;

public class EnginePersist {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();

    public Engine getById(String id) {
        Engine result;
        try (SessionFactory sessionFactory = new MetadataSources(this.registry).buildMetadata().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            result = session.get(Engine.class, Integer.parseInt(id));
            session.getTransaction().commit();
        }
        return result;
    }
}
