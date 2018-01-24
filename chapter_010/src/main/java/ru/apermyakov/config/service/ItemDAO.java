package ru.apermyakov.config.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.apermyakov.config.item.Item;

import java.util.List;

public class ItemDAO implements DAO<Item> {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();

    public void create(Item item) {
        try (SessionFactory sessionFactory = new MetadataSources(this.registry).buildMetadata().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        }
    }

    public List<Item> findAll() {
        List<Item> result;
        try (SessionFactory sessionFactory = new MetadataSources(this.registry).buildMetadata().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            result = (List<Item>) session.createQuery( "from Item").list();
            session.getTransaction().commit();
        }
        return result;
    }
}
