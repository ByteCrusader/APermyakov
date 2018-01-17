package ru.apermyakov.ioc.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.apermyakov.ioc.dbworker.DbWorker;
import ru.apermyakov.ioc.picker.DbPicker;
import ru.apermyakov.ioc.picker.JdbcPicker;
import ru.apermyakov.ioc.user.User;

/**
 * Class for user import.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
@Component
public class UserImport implements Import {

    /**
     * Field for data base worker.
     */
    private DbWorker dbWorker;

    @Autowired
    public UserImport(DbWorker dbWorker) {
        this.dbWorker = dbWorker;
    }

    /**
     * Method for import user.
     *
     * @param user user.
     */
    @Override
    public void importUser(User user) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        DbPicker picker = context.getBean(JdbcPicker.class);
        picker.makeConnSettings(this.dbWorker.buildConnSettings());
        picker.add(user);
    }
}
