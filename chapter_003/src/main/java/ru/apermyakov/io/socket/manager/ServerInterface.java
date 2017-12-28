package ru.apermyakov.io.socket.manager;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Interface to modulate server activity.
 *
 * @author apermyakov
 * @version 1.0
 * @since 27.12.2017
 */
public interface ServerInterface {

    /**
     * Method for get root catalog into properties.
     */
    List<String> getRootCatalog();

    /**
     * Method for go to sub catalog of root catalog.
     *
     * @param message
     */
    String goToSubCatalog(String message);

    /**
     * Method for go to parent catalog of root catalog.
     *
     * @param message
     */
    String goToParentCatalog(String message);

    /**
     * Method for download to client file by file's name.
     */
    String downloadFile(File file) throws IOException;

    /**
     * Method for upload file to catalog from client.
     */
    String uploadFile(File file) throws IOException;

    /**
     * Method for initial server.
     */
    void initialServer() throws IOException;

    /**
     * Method for start server.
     */
    void startServer() throws IOException;
}
