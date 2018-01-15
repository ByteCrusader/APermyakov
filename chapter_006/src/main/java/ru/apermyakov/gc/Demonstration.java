package ru.apermyakov.gc;

import ru.apermyakov.gc.user.CollectorUser;
import ru.apermyakov.gc.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for demonstrate gc work.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 15.01.2018.
 */
public class Demonstration {

    /**
     * Method for show heap info.
     */
    private static void heapInfo() {
        int megaBytes = 1024*1024;

        Runtime runtime = Runtime.getRuntime();

        System.out.format("Used memory : %s%s", (runtime.totalMemory() - runtime.freeMemory()) / megaBytes, System.lineSeparator());

        System.out.format("Free memory : %s%s", runtime.freeMemory() / megaBytes, System.lineSeparator());

        System.out.format("Total memory : %s%s", runtime.totalMemory() / megaBytes, System.lineSeparator());

        System.out.format("Max memory : %s%s", runtime.maxMemory() / megaBytes, System.lineSeparator());
    }

    /**
     * Main method.
     *
     * @param args args.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start");
        List<User> userList = new ArrayList<>();
        System.out.println("Initialize 16000");
        for (int index = 1; index <= 16000; index++) {
            userList.add(new CollectorUser(String.valueOf(index), String.valueOf(index)));
        }
        heapInfo();
        System.out.println("Clear");
        userList.clear();
        heapInfo();
        Thread.sleep(1000);
        System.out.println("End");
    }
}
