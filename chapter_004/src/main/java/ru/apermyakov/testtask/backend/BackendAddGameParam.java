package ru.apermyakov.testtask.backend;

import ru.apermyakov.testtask.board.Board;
import ru.apermyakov.testtask.user.User;

/**
 * Interface for game parameters.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public interface BackendAddGameParam {

    /**
     * Method for add board.
     *
     * @param board board.
     */
    void addBoard(Board board);

    /**
     * Method for add user.
     *
     * @param user user.
     */
    void addUser(User user);
}
