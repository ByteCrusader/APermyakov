package ru.apermyakov.testtask;

import java.io.IOException;

/**
 * Interface for modulate main actions for test task.
 *
 * @author apermyakov
 * @version 1.0
 * @since 28.12.2017
 */
public interface TestTaskInterface {

    /**
     * Method for initial search action.
     *
     * @param inputMessage user input
     * @throws IOException e
     */
    void find(String[] inputMessage) throws IOException;

    /**
     * Method for save target directory.
     *
     * @param directory directory
     */
    void goToDirectory(String directory);

    /**
     * Method for save search data to filter.
     *
     * @param searchData search data
     */
    void collectSearchData(String searchData);

    /**
     * Method for initial search by file's mask.
     */
    void searchByMask();

    /**
     * Method for initial search by file's name.
     */
    void searchByName();

    /**
     * Method for initial search by regular expression.
     */
    void searchByRegx();

    /**
     * Method for write results to file.
     *
     * @param targetFile target file
     * @throws IOException e
     */
    void whiteToFile(String targetFile) throws IOException;

    /**
     * Method for show help to user.
     *
     * @throws IOException e
     */
    void showHelp() throws IOException;

    /**
     * Method for key validate.
     *
     * @param key key
     * @param argument argument
     * @throws IOException e
     */
    void keyValidate(String key, String argument) throws IOException;
}
