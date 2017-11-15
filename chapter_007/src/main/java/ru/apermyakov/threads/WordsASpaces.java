package ru.apermyakov.threads;

/**
 * Class for modulate threads to find words and spaced in text.
 *
 * @author apermyakov 14.11.2017
 * @version 1.0
 * @since 14.11.2017
 */
public class WordsASpaces {

    /**
     * Private class for search words in text with ignore wrong spaces.
     *
     * @author apermyakov
     * @version 1.0
     * @since 14.11.2017
     */
    private static class Words implements Runnable {

        /**
         * Field for contain string text.
         */
        private String string;

        /**
         * Design words object.
         *
         * @param string insert text
         */
        Words(String string) {
            this.string = string;
        }

        /**
         * Method for modulate run threads action to find words.
         */
        @Override
        public void run() {
            int counter = 0;
            int spaceBeforeWord;
            int spaceAfterWord = 0;

            for (int index = 0; index < string.length(); index++) {
                if (string.charAt(index) == ' ') {
                    spaceBeforeWord = index;
                    if (spaceBeforeWord - spaceAfterWord > 1) {
                            counter++;
                    }
                    spaceAfterWord = index;
                }
            }

            if (string.length() - spaceAfterWord > 1) {
                counter++;
            }
            System.out.println(String.format("%s words.", counter));
        }
    }

    /**
     * Private class for search spaces in text.
     *
     * @author apermyakov
     * @version 1.0
     * @since 15.11.2017
     */
    private static class Spaces implements Runnable {

        /**
         * Field for contain string text.
         */
        private String string;

        /**
         * Design spaces object.
         *
         * @param string insert text
         */
        Spaces(String string) {
            this.string = string;
        }

        /**
         * Method for modulate thread run action to find spaces.
         */
        @Override
        public void run() {
            int counter = 0;

            for (char symbol : string.toCharArray()) {
                if (symbol == ' ') {
                    counter++;
                }
            }

            System.out.println(String.format("%s spaces.", counter));
        }
    }

    /**
     * Private class for interact with user at the beginning and end of app.
     *
     * @author apermyakov
     * @version 1.0
     * @since 15.11.2017
     */
    private static class Receptionist implements Runnable {

        /**
         * Field for phrase to user.
         */
        private String string;

        /**
         * Design receptionist.
         *
         * @param string output text
         */
        Receptionist(String string) {
            this.string = string;
        }

        /**
         * Method for output phrase for user.
         */
        @Override
        public void run() {
            System.out.println(string);
        }
    }

    /**
     * Method for initialize threads running.
     *
     * @param string insert text
     */
    public static void calculate(String string) {
        Thread hello = new Thread(new Receptionist("Hello! Program starts up.."));
        Thread bye = new Thread(new Receptionist("Program is finished its work. Bye!"));
        Thread findWords = new Thread(new Words(string));
        Thread findSpaces = new Thread(new Spaces(string));

        hello.start();

        try {
            hello.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        findWords.start();
        findSpaces.start();

        while (findWords.isAlive() || findSpaces.isAlive());

        bye.start();
    }
}
