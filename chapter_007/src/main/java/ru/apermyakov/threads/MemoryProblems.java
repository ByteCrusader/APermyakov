package ru.apermyakov.threads;

/**
 * Class for modulate JMM problems by threads.
 *
 * @author apermyakov
 * @version 1.0
 * @since 16.11.2017
 */
public class MemoryProblems {

    /**
     * Class for modulate increment thread.
     *
     * @author apermyakov
     * @version 1.0
     * @since 16.11.2017
     */
    public static class IncrementThread implements Runnable {

        /**
         * Method for run increment thread.
         */
        @Override
        public void run() {
            increaseObject();
        }

        /**
         * Method for increase object and output.
         */
        private void increaseObject() {

            System.out.println("Increment thread starting up");

            System.out.println("Incrementer sleep");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Incrementer wake up");

            SharedObject localObject = SharedObject.initial;
            localObject.sharedObject++;
            System.out.println(String.format("Increment object - %s", localObject));

            System.out.println("Incrementer sleep");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Incrementer wake up and stop");
        }
    }

    /**
     * Class for modulate read thread.
     *
     * @author apermyakov
     * @version 1.0
     * @since 16.11.2017
     */
    public static class ReadThread implements Runnable {

        /**
         * Method for run read thread.
         */
        @Override
        public void run() {
            readObject();
        }

        /**
         * Method for read object and output.
         */
        private void readObject() {

            System.out.println("Read thread starting up");

            SharedObject localObject = SharedObject.initial;
            System.out.println(String.format("Read object - %s", localObject));

            System.out.println("Reader loop start");

            while (localObject.sharedObject == 10);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Reader loop end");

            System.out.println(String.format("Read object - %s", localObject));

            System.out.println("Reader stop");

        }
    }

    /**
     * Class for modulate shared object in heap.
     *
     * @author apermyakov
     * @version 1.0
     * @since 16.11.2017
     */
    public static class SharedObject {

        /**
         * Field for modulate shared object.
         */
        public volatile Integer sharedObject = 10;

        /**
         * Field for initial shared object.
         */
        public static SharedObject initial = new SharedObject();

        /**
         * Method for override to strong method.
         *
         * @return shared object to string
         */
        @Override
        public String toString() {
            return sharedObject.toString();
        }
    }

    /**
     * Method for check visibility of threads and build infinitely loop.
     */
    private static void buildInfinitelyLoop() {
        Thread increment = new Thread(new MemoryProblems.IncrementThread());
        Thread read = new Thread(new MemoryProblems.ReadThread());

        increment.start();
        read.start();
    }

    /**
     * Method for check race condition of threads.
     */
    private static void buildRaceCondition() {
        Thread incrementOne = new Thread(new MemoryProblems.IncrementThread());
        Thread incrementTwo = new Thread(new MemoryProblems.IncrementThread());
        Thread read = new Thread(new MemoryProblems.ReadThread());

        incrementOne.start();
        incrementTwo.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        read.start();
    }

    /**
     * Main method.
     *
     * @param args args
     */
    public static void main(String[] args) {
        buildRaceCondition();
        buildInfinitelyLoop();
    }
}
