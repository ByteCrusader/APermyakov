package ru.apermyakov.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;

/**
 * Class for thread pool.
 *
 * @author apermyakov
 * @version 1.0
 * @since 20.11.2017
 */
@ThreadSafe
public class ThreadPool {

    /**
     * Class for build work.
     *
     * @author apermyakov
     * @version 1.0
     * @since 20.11.2017
     */
    public static class Work {

        /**
         * Field for name if work.
         */
        String name;

        /**
         * Field for time of work.
         */
        long time;

        /**
         * Design work.
         *
         * @param name name
         * @param time time
         */
        Work(String name, long time) {
            this.name = name;
            this.time = time;
        }
    }

    /**
     * Field for number of threads.
     */
    @GuardedBy("listOfWorks")
    private final int numberOfThreads;

    /**
     * Field for list of work.
     */
    @GuardedBy("itself")
    private final LinkedList<Work> listOfWorks;

    /**
     * Field for pool array.
     */
    @GuardedBy("listOfWorks")
    private final Thread[] pool;

    /**
     * Design thread pool.
     *
     * @param numberOfThreads number of threads
     */
    public ThreadPool(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
        this.listOfWorks = new LinkedList<>();
        this.pool = new Thread[numberOfThreads];
    }

    /**
     * Method for initial threads pool.
     */
    public void initialPool() {
        for (int index = 0; index < numberOfThreads; index++) {
            pool[index] = new Thread(new Pool());
            pool[index].start();
        }
    }

    /**
     * Method for add work to list of work.
     *
     * @param work work
     */
    public void add(Work work) {
        synchronized (listOfWorks) {
            listOfWorks.add(work);
            listOfWorks.notify();
        }
    }

    /**
     * Field for thread counter to output.
     */
    private int threadCounter = 1;

    /**
     * Class for modulate pool.
     *
     * @author apermyakov
     * @version 1.0
     * @since 20.11.2017
     */
    private class Pool implements Runnable {

        /**
         * Method for override run.
         */
        @Override
        public void run() {

            int threadNumber = threadCounter++;

            Work threadWork;

            System.out.println("Initialize " + threadNumber + " thread");

            while (true) {
                synchronized (listOfWorks) {
                    while (listOfWorks.isEmpty()) {
                        try {
                            System.out.println("Thread " + threadNumber + " wait");
                            listOfWorks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    threadWork = listOfWorks.removeFirst();
                }

                try {
                    Thread.sleep(threadWork.time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread " + threadNumber + " done " + threadWork.name);
            }
        }
    }

    /**
     * Main method.
     *
     * @param args args
     */
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(4);
        pool.initialPool();

        pool.add(new Work("First work", 1000));
        pool.add(new Work("Second work", 1000));
        pool.add(new Work("Third work", 1000));
        pool.add(new Work("Fourth work", 1000));
        pool.add(new Work("Fifth work", 1000));
    }
}
