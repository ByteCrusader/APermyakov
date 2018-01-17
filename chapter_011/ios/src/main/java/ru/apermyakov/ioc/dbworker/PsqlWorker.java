package ru.apermyakov.ioc.dbworker;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for psql worker.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
@Component
public class PsqlWorker implements DbWorker {

    /**
     * Method for build connection settings.
     *
     * @return map of settings.
     */
    @Override
    public Map<String, String> buildConnSettings() {
        Map<String, String> settings = new HashMap<>();
        settings.put("driver", "org.postgresql.Driver");
        settings.put("url", "jdbc:postgresql://localhost:5432/spring");
        settings.put("userName", "postgres");
        settings.put("userPass", "Figa1357");
        return settings;
    }
}
