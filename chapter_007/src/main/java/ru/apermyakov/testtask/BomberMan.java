package ru.apermyakov.testtask;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class for modulate BomberMan game.
 *
 * @author apermyakov
 * @version 1.0
 * @since 22.11.2017
 */
public class BomberMan {

    /**
     * Field for board.
     */
    private ReentrantLock[][] board = new ReentrantLock[0][0];

    /**
     * Field for number of board lines.
     */
    private int numberOfBoardLines;

    /**
     * Field for number of board columns.
     */
    private int numberOfBoardColumns;

    /**
     * Design BomberMan.
     *
     * @param numberOfBoardLines number of board lines
     * @param numberOfBoardColumns number of board columns
     */
    public BomberMan(int numberOfBoardLines, int numberOfBoardColumns) {
        this.numberOfBoardLines = numberOfBoardLines;
        this.numberOfBoardColumns = numberOfBoardColumns;
        this.board = new ReentrantLock[numberOfBoardLines][numberOfBoardColumns];
    }

    /**
     * Field for board lock.
     */
    private final Object boardLock = new Object();

    /**
     * Field for initial board lock.
     */
    private final Object initialLock = new Object();

    /**
     * Method for initial board objects and eternal obstacles.
     */
    private void initialEternalObstacles() {
        synchronized (initialLock) {
            for (int outsideIndex = 0; outsideIndex < this.numberOfBoardLines; outsideIndex++) {
                for (int insideIndex = 0; insideIndex < this.numberOfBoardColumns; insideIndex++) {
                    board[outsideIndex][insideIndex] = new ReentrantLock();
                    if (outsideIndex % 2 != 0 && insideIndex % 2 != 0) {
                        System.out.println(String.format("Board [%s] [%s] is locked", outsideIndex,  insideIndex));
                        board[outsideIndex][insideIndex].lock();
                    }
                }
            }
        }
    }

    /**
     * Method for random initial destructible obstacles.
     *
     * @throws InterruptedException interrupted thread
     */
    private void randomInitialDestructibleObstacles() throws InterruptedException {
        int numberOfDestructibleObstacles = 5;
        Random random = new Random();
        int initialized = 0;
        synchronized (initialLock) {
            while (initialized < numberOfDestructibleObstacles) {
                if (board[random.nextInt(this.numberOfBoardLines)][random.nextInt(this.numberOfBoardColumns)].tryLock(10L, TimeUnit.MILLISECONDS)) {
                    System.out.println("Destructible obstacle initialized");
                    initialized++;
                } else {
                    System.out.println("Locked");
                }
            }
        }
    }

    /**
     * Method for initial game board.
     *
     * @throws InterruptedException interrupted thread
     */
    public void initialBoard() throws InterruptedException {
        synchronized (boardLock) {
            Thread initialEternalObstaclesThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    initialEternalObstacles();
                }
            });
            Thread randomInitialDestructibleObstaclesThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        randomInitialDestructibleObstacles();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            initialEternalObstaclesThread.start();
            initialEternalObstaclesThread.join();
            randomInitialDestructibleObstaclesThread.start();
            randomInitialDestructibleObstaclesThread.join();
        }
    }

    /**
     * Method for move bomberman.
     *
     * @param nextPoint move to this point
     * @param actualPoint actual point
     * @return actual point
     * @throws InterruptedException interrupted thread
     */
    private int[] move(int[] nextPoint, int[] actualPoint) throws InterruptedException {
        int[] result;

        if (board[nextPoint[0]][nextPoint[1]].tryLock(500L, TimeUnit.MILLISECONDS)) {
            result = nextPoint;
            System.out.println(String.format("Bomberman move at [%s] [%s]", nextPoint[0],  nextPoint[1]));
            board[actualPoint[0]][actualPoint[1]].unlock();
            Thread.sleep(1000);
        } else {
            System.out.println(String.format("Locked move at [%s] [%s]", nextPoint[0],  nextPoint[1]));
            result = actualPoint;
        }
        return result;
    }

    /**
     * Method for try random move per point around bomberman.
     *
     * @param pointYIndex x coordinate for point
     * @param pointXIndex y coordinate for point
     * @return coordinate of actual point of bomberman
     * @throws InterruptedException interrupted thread
     */
    private int[] tryMove(int pointYIndex, int pointXIndex) throws InterruptedException {
        Random random = new Random();
        int[] result = new int[] {pointYIndex, pointXIndex};
        int randomeDirection = random.nextInt(4) + 1;

        if (randomeDirection == 1) {
            if (pointYIndex - 1 >= 0) {
                result = move(new int[] {pointYIndex - 1, pointXIndex}, result);
            }
        } else if (randomeDirection == 2) {
            if (pointXIndex + 1 <= numberOfBoardColumns - 1) {
                result = move(new int[] {pointYIndex, pointXIndex + 1}, result);
            }
        } else if (randomeDirection == 3) {
            if (pointYIndex + 1 <= numberOfBoardLines - 1) {
                result = move(new int[] {pointYIndex + 1, pointXIndex}, result);
            }
        } else if (randomeDirection == 4) {
            if (pointXIndex - 1 >= 0) {
                result = move(new int[] {pointYIndex, pointXIndex - 1}, result);
            }
        }
        return result;
    }

    /**
     * Method for initial bomberman move.
     *
     * @throws InterruptedException interrupted thread
     */
    public void bombermanMoveInitial() throws InterruptedException {
        Random random = new Random();

        int[] bombermanPoint = new int[] {random.nextInt(this.numberOfBoardLines), random.nextInt(this.numberOfBoardColumns)};

        if (board[bombermanPoint[0]][bombermanPoint[1]].tryLock(100L, TimeUnit.MILLISECONDS)) {
            System.out.println(String.format("Bomberman initial at [%s] [%s]", (bombermanPoint[0]),  bombermanPoint[1]));
            while (true) {
                bombermanPoint = tryMove(bombermanPoint[0], bombermanPoint[1]);
            }
        } else {
            bombermanMoveInitial();
        }
    }

    /**
     * Main method.
     *
     * @param args args
     */
    public static void main(String[] args) {
        BomberMan game = new BomberMan(11, 15);

        Thread initialThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    game.initialBoard();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread initialBomberman = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    game.bombermanMoveInitial();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        initialThread.start();
        try {
            initialThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        initialBomberman.start();
    }
}
