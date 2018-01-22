package ru.apermyakov.lambda;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class for test hibernate template.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 22.01.2018.
 */
public class HibernateTemplateTest {

    /**
     * Field for template.
     */
    private final HibernateTemplate template = new HibernateTemplate();

    /**
     * Method for mock session.
     *
     * @return session.
     */
    private Session session() {
        Session session = mock(Session.class);
        Transaction transaction = mock(Transaction.class);
        when(session.beginTransaction()).thenReturn(transaction);
        return session;
    }

    @Test
    public void whenCheckWordThenTakeString() {
        Function<Session, String> transaction = this.template.tx(session -> "check");
        String result = transaction.apply(this.session());
        assertThat(result, is("check"));
    }

    @Test
    public void whenSaveThenTakeSerializable() {
        Function<Session, Serializable> transaction = this.template.tx(session -> session.save("check"));
        Serializable result = null;
        assertThat(transaction.apply(this.session()), is(result));
    }

    @Test
    public void whenFindThenTakeList() {
        Function<Session, List<Object>> transaction = this.template.tx(session -> session.createQuery("").list());
        List<Object> result = null;
        assertThat(transaction.apply(this.session()), is(result));
    }

    @Test
    public void whenUpdateThenTakeTrue() {
        Function<Session, Boolean> transaction = this.template.tx(session -> {
            session.update("check");
            return true;}
            );
        assertThat(transaction.apply(this.session()), is(true));
    }

    @Test
    public void whenDeleteThenTakeTrue() {
        Function<Session, Boolean> transaction = this.template.tx(session -> {
            session.delete("check");
            return true;});
        assertThat(transaction.apply(this.session()), is(true));
    }
}