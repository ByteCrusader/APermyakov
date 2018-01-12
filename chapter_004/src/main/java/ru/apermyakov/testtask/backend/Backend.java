package ru.apermyakov.testtask.backend;

/**
 * Main interface for tic-tac-toe backend.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public interface Backend extends BackendInitial, BackendStart, PrioritySort, BackendAddGameParam, BackendCheckWin, BackendLoop, BackendRestart, BackendUserWork {
}
