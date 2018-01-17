package ru.apermyakov.ioc.picker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.apermyakov.ioc.storage.AdminStorage;
import ru.apermyakov.ioc.user.User;

/**
 * Class for demonstrate annotation spring.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
@Component
public class AdminPicker implements Picker {

    /**
     * Field for annotation storage.
     */
    private final AdminStorage storage;

    @Autowired
    public AdminPicker(AdminStorage storage) {
        this.storage = storage;
    }

    /**
     * Method for add user.
     *
     * @param user user.
     */
    @Override
    public void add(User user) {
        this.storage.add(user);
    }
}
