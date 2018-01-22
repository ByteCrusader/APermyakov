package ru.apermyakov.lambda;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Function;

/**
 * Class for hibernate template.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 22.01.2018.
 */
public class HibernateTemplate {

    /**
     * Template method.
     *
     * @param actions actions.
     * @param <T> input type.
     * @return result of session.
     */
    public <T> Function<Session, T> tx(Function<Session, T> actions) {
        return session -> {
            T result = null;
            Transaction transaction = session.beginTransaction();
            try {
                result = actions.apply(session);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            } finally {
                session.close();
            }
            return result;
        };
    }
}
