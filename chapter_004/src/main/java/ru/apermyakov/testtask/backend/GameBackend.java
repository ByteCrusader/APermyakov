package ru.apermyakov.testtask.backend;

import ru.apermyakov.testtask.board.Board;
import ru.apermyakov.testtask.board.GameBoard;
import ru.apermyakov.testtask.cell.Sell;
import ru.apermyakov.testtask.output.Output;
import ru.apermyakov.testtask.output.UserOutput;
import ru.apermyakov.testtask.requests.ComputerRequest;
import ru.apermyakov.testtask.requests.UserRequests;
import ru.apermyakov.testtask.user.BoardUser;
import ru.apermyakov.testtask.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class for modulate tic-tac-toe backend for user and computer.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 12.01.2018.
 */
public class GameBackend implements Backend {

    /**
     * Field for game board.
     */
    private Board gameBoard;

    /**
     * Field for map of board size.
     */
    private Map<String, Integer> boardSize;

    /**
     * Field for list of game users.
     */
    private List<User> users = new ArrayList<>();

    /**
     * Field for output.
     */
    private Output output;

    /**
     * Field for complexity of game.
     */
    private int complexity;

    /**
     * Method for add board.
     *
     * @param board board.
     */
    @Override
    public void addBoard(Board board) {
        this.gameBoard = board;
        this.gameBoard.initialBoard();
    }

    /**
     * Method for add user.
     *
     * @param user user.
     */
    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    /**
     * Method for initial backend.
     */
    @Override
    public void initialBackend() {
        this.output = new UserOutput();
        User user = new BoardUser("User", true);
        user.setPequests(new UserRequests());
        this.addUser(user);
        this.boardSize = this.users.stream().filter(User::isUser).findFirst().get().getRequests().requestSize();
        User comp = new BoardUser("Comp", false);
        comp.setPequests(new ComputerRequest(boardSize.get("Height"), boardSize.get("Width")));
        this.addUser(comp);
        this.addBoard(new GameBoard(boardSize.get("Height"), boardSize.get("Width")));
        this.users.stream().filter(User::isUser).findFirst().get().getRequests().requestValue(this.users);
        this.users.stream().filter(User::isUser).findFirst().get().getRequests().requestPriority(this.users);
        this.complexity = this.users.stream().filter(User::isUser).findFirst().get().getRequests().requestComplexity();
        this.sortUsersByPriority();
    }

    /**
     * Method for get coordinates from user.
     *
     * @param user user.
     * @return map of coordinates.
     */
    protected Map<String, Integer> getCoordinates(User user) {
        Map<String, Integer> coordinates;
        do {
            coordinates = this.users.stream().filter(user::equals).findFirst().get().getRequests().requestCoordinates();
        } while (this.gameBoard.selectSell(coordinates.get("Height"), coordinates.get("Width")).isValueSet());
        return coordinates;
    }

    /**
     * Method for set sell value.
     *
     * @param coordinates coordinates.
     * @param user user.
     */
    protected void setSellValue(Map<String, Integer> coordinates, User user) {
        this.gameBoard.setValue(coordinates.get("Height"), coordinates.get("Width"), user.isCross());
    }

    /**
     * Method for check step to end game.
     *
     * @param coordinates coordinates.
     * @param user user.
     * @return is end.
     */
    protected boolean checkStepToEndGame(Map<String, Integer> coordinates, User user) {
        boolean continueGame = true;
        if (isFull()) {
            this.output.sendMessage("Draw");
            continueGame = false;
        } else if (checkWin(gameBoard.selectSell(coordinates.get("Height"), coordinates.get("Width")))) {
            this.output.sendMessage(String.format("%s win", user.getName()));
            continueGame = false;
        }
        return continueGame;
    }

    /**
     * Method for work with user.
     *
     * @param user user.
     * @return result of work.
     */
    @Override
    public boolean workWithUser(User user) {
        Map<String, Integer> coordinates;
        coordinates = getCoordinates(user);
        setSellValue(coordinates, user);
        this.output.sendMessage(String.format("%s step :", user.getName()));
        this.output.sendMessage(this.gameBoard.getBoard());
        return checkStepToEndGame(coordinates, user);
    }

    /**
     * Method for start main backend loop.
     */
    @Override
    public void startMainLoop() {
        boolean continueGame = true;
        this.output.sendMessage(this.gameBoard.getBoard());
        do {
            for (User user : this.users) {
                continueGame = workWithUser(user);
                if (!continueGame) {
                    break;
                }
            }
        } while (continueGame);
    }

    /**
     * Method for reset backend.
     */
    @Override
    public void resetGame() {
        this.users.clear();
        this.initialBackend();
    }

    /**
     * Method for start backend activity.
     */
    @Override
    public void startBackend() {
        boolean continueGame;
        do {
            this.startMainLoop();
            continueGame = this.users.stream().filter(User::isUser).findFirst().get().getRequests().continueGame();
            if (continueGame) {
                this.resetGame();
            }
        } while (continueGame);
    }

    /**
     * Method for sort users by priority.
     */
    @Override
    public void sortUsersByPriority() {
        this.users.sort((o1, o2) -> o2.getPriority() - o1.getPriority());
    }

    /**
     * Generic method for check board vertical or horizontal line to win.
     *
     * @param sell sell
     * @param horizon true if horizon.
     * @return is win.
     */
    protected boolean checkGenericLine(Sell sell, boolean horizon) {
        int coincidences = 0;
        boolean foundFirstValue = false;
        for (int index = 1; index <= this.boardSize.get(horizon ? "Width" : "Height"); index++) {
            Sell selectedSell = this.gameBoard.selectSell(horizon ? sell.getHeight() : index, horizon ? index : sell.getWidth());
            if (selectedSell.isValueSet()) {
                foundFirstValue = true;
                if (selectedSell.isCross() == sell.isCross()) {
                    coincidences++;
                } else if (coincidences != 0) {
                    break;
                }
            } else if (foundFirstValue) {
                break;
            }
        }
        return coincidences == this.complexity;
    }

    /**
     * Method for check board for full.
     *
     * @return is full.
     */
    protected boolean isFull() {
        boolean full = true;
        for (Sell sell : this.gameBoard.getSells()) {
            if (!sell.isValueSet()) {
                full = false;
                break;
            }
        }
        return full;
    }

    /**
     * Method for check horizontal to win.
     *
     * @param sell sell.
     * @return is win.
     */
    protected boolean checkHorizontal(Sell sell) {
        return checkGenericLine(sell, true);
    }

    /**
     * Method for check vertical to win.
     *
     * @param sell sell.
     * @return is win.
     */
    protected boolean checkVertical(Sell sell) {
        return checkGenericLine(sell, false);
    }

    /**
     * Generic method for check all diagonals.
     *
     * @param sell sell.
     * @param right true if move to right.
     * @param up true if move to up.
     * @return is win.
     */
    protected boolean checkGenericDiagonal(Sell sell, boolean right, boolean up) {
        int sellHeight = sell.getHeight();
        int sellWidth = sell.getWidth();
        int coincidences = 0;
        boolean foundFirstValue = false;
        for (; sellHeight <= this.boardSize.get("Height") && sellWidth <= this.boardSize.get("Width") && sellHeight > 0 && sellWidth > 0;) {
            if (foundFirstValue) {
                if (this.gameBoard.selectSell(sellHeight, sellWidth).isValueSet() && this.gameBoard.selectSell(sellHeight, sellWidth).isCross() == sell.isCross()) {
                    coincidences++;
                } else {
                    break;
                }
            }
            sellHeight = up ? ++sellHeight : --sellHeight;
            sellWidth = right ? ++sellWidth : --sellWidth;
            foundFirstValue = true;
        }
        return coincidences + 1 >= this.complexity;
    }

    /**
     * Method for initial diagonal check.
     *
     * @param sell sell.
     * @return is win.
     */
    protected boolean checkDiagonal(Sell sell) {
        return checkGenericDiagonal(sell, true, true)
                || checkGenericDiagonal(sell, true, false)
                || checkGenericDiagonal(sell, false, true)
                || checkGenericDiagonal(sell, false, false);
    }

    /**
     * Method for check win by sell.
     *
     * @param sell sell.
     * @return win or not.
     */
    @Override
    public boolean checkWin(Sell sell) {
        return checkHorizontal(sell) || checkVertical(sell) || checkDiagonal(sell);
    }

    /**
     * Method for test backend.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        GameBackend backend = new GameBackend();
        backend.initialBackend();
        backend.startBackend();
    }
}
