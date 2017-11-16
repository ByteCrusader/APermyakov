package ru.apermyakov.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class for modulate save thread counter.
 *
 * @author apermyakov
 * @version 1.0
 * @since 16.11.2017
 */
public class SynchCount {

    /**
     * Class for modulate counter.
     *
     * @author apermyakov
     * @version 1.0
     * @since 16.11.2017
     */
    @ThreadSafe
    public static class Count {

        /**
         * Status field.
         */
        @GuardedBy("this")
        private int count = 0;

        /**
         * Method for increment counter.
         *
         * @return counter
         */
        public int increment() {
            synchronized (this) {
                return ++this.count;
            }
        }
    }

    /**
     * class for modulate counter thread.
     *
     * @author apermyakov
     * @version 1.0
     * @since 16.11.2017
     */
    public static class CountThread implements Runnable {

        /**
         * Status field.
         */
        protected final Count counter;

        /**
         * Design thread.
         *
         * @param counter counter
         */
        public CountThread(Count counter) {
            this.counter = counter;
        }

        /**
         * Method for modulate thread action.
         */
        @Override
        public void run() {

            System.out.println(String.format("Counter = %s", counter.increment()));
        }
    }

    /**
     * Main method.
     *
     * @param args args
     */
    public static void main(String[] args) {

        Count counter = new Count();

        Thread threadOne = new Thread(new CountThread(counter));
        Thread threadTwo = new Thread(new CountThread(counter));

        threadOne.start();
        threadTwo.start();
    }
}
