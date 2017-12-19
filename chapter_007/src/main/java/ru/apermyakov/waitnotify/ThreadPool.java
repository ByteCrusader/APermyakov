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
     * Field for identify pool action.
     */
    private volatile boolean work;

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
        work = true;
    }

    /**
     * Method for add work to list of work.
     *
     * @param work work
     */
    public void add(Work work) {
        synchronized (listOfWorks) {
            if (this.work) {
                listOfWorks.add(work);
                listOfWorks.notify();
            } else {
                System.out.println("Pool stopped!");
            }
        }
    }

    /**
     * Method for end pool.
     */
    public void endPool() {
        System.out.println("End pool");
        synchronized (listOfWorks) {
            for (int index = 0; index < numberOfThreads; index++) {
                this.pool[index].interrupt();
            }
            work = false;
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

            Work threadWork = new Work("", 0L);

            System.out.println("Initialize " + threadNumber + " thread");

            while (!Thread.currentThread().isInterrupted()) {
                synchronized (listOfWorks) {
                    while (!Thread.currentThread().isInterrupted() && listOfWorks.isEmpty()) {
                        try {
                            System.out.println("Thread " + threadNumber + " wait");
                            listOfWorks.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    if (work) {
                        threadWork = listOfWorks.removeFirst();
                    }
                }

                try {
                    Thread.sleep(threadWork.time);
                } catch (InterruptedException e) {
                    System.out.println("Thread " + threadNumber + " done his work");
                    Thread.currentThread().interrupt();
                }

                if (work) {
                    System.out.println("Thread " + threadNumber + " done " + threadWork.name);
                }
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

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pool.endPool();

        pool.add(new Work("First work", 1000));
    }
}
