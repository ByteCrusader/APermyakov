package ru.apermyakov.io.socket.manager;

import java.io.IOException;

/**
 * Interface to modulate client activity.
 *
 * @author apermyakov
 * @version 1.0
 * @since 27.12.2017
 */
public interface ClientInterface {

    /**
     * Method for initial client.
     */
    void initialClient() throws IOException;

    /**
     * Method for start client.
     */
    void startClient() throws IOException;
}
