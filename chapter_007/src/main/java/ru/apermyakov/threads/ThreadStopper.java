package ru.apermyakov.threads;

/**
 * Class for modulate threads stop.
 *
 * @author apermyakov
 * @version 1.0
 * @since 15.11.2017
 */
public class ThreadStopper {

    /**
     * Class for build time thread.
     *
     * @author apermyakov
     * @version 1.0
     * @since 15.11.2017
     */
    static class Time implements Runnable {

        /**
         * Field for time of app action.
         */
        private long time;

        /**
         * Design time thread.
         *
         * @param time correct time of app
         */
        Time(long time) {
            this.time = time;
        }

        /**
         * Method for modulate time thread action.
         */
        @Override
        public void run() {
            System.out.println("Start time.");

            try {
                System.out.println("Time thread sleep.");
                Thread.sleep(time);
                System.out.println("Time thread wake up.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Stop time.");
        }
    }

    /**
     * Class for build char counter thread.
     *
     * @author apermyakov
     * @version 1.0
     * @since 15.11.2017
     */
    static class CountChar implements Runnable {

        /**
         * Field for analysis text.
         */
        private String string;

        /**
         * Design char counter thread.
         *
         * @param string insert text
         */
        CountChar(String string) {
            this.string = string;
        }

        /**
         * Method for modulate char counter thread.
         */
        @Override
        public void run() {
            int counter = 0;

            System.out.println("Calculate started.");

            do {
                counter++;

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Calculate stopped.");
                    break;
                }

            } while(!Thread.interrupted() && counter < string.length());

            if (counter == string.length()) {
                System.out.println(String.format("Successful end of calculate - %s char.", counter));
            }
        }
    }

    /**
     * Method for initialize threads.
     *
     * @param string insert text
     * @param timeInMs needed time in milli sec
     */
    public static void initialize(String string, long timeInMs) {
        Thread time = new Thread(new Time(timeInMs));
        Thread count = new Thread(new CountChar(string));

        time.start();
        count.start();

        while (time.isAlive());
        count.interrupt();
    }
}
