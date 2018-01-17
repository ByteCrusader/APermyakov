package ru.apermyakov.ioc.storage;

import org.springframework.stereotype.Component;
import ru.apermyakov.ioc.user.User;

/**
 * Class for annotation storage.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
@Component
public class JdbcStorage implements AdminStorage {

    /**
     * Method for add user.
     *
     * @param user user.
     */
    @Override
    public void add(User user) {
        System.out.println("Add to DB");
    }
}
