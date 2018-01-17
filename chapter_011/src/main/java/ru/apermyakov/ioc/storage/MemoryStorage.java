package ru.apermyakov.ioc.storage;

import ru.apermyakov.ioc.user.User;

/**
 * Class for xml storage.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
public class MemoryStorage implements Storage {

    /**
     * Method for add user.
     *
     * @param user user.
     */
    @Override
    public void add(User user) {
        System.out.println("Add to memory");
    }
}
