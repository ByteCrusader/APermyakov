package ru.apermyakov.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.List;

/**
 * Class for modulate producer customer pattern.
 *
 * @author apermyakov
 * @version 1.0
 * @since 20.11.2017
 */
@ThreadSafe
public class ProducerCustomer {

    /**
     * Field for data container.
     */
    @GuardedBy("lock")
    private final List<String> data = new LinkedList<>();

    /**
     * Field for lock.
     */
    @GuardedBy("itself")
    private final Object lock = new Object();

    /**
     * Field for condition of work.
     */
    @GuardedBy("lock")
    private boolean enable = true;

    /**
     * Method for catch data by customer.
     *
     * @throws InterruptedException e
     */
    public void customerCatchData() throws InterruptedException {
        synchronized (this.lock) {
            while (this.enable) {
                if (this.data.size() == 0) {
                    System.out.println("Customer wait data");
                    this.lock.wait();
                } else {
                    System.out.println("Customer catch data");
                    this.data.remove(this.data.size() - 1);
                }
            }
        }
    }

    /**
     * Method for add data by producer.
     */
    public void producerAddData() {
        synchronized (this.lock) {
            System.out.println("Producer add data");
            this.data.add("Data");
            this.lock.notify();
        }
    }

    /**
     * Method for build end of data.
     */
    public void endOfDay() {
        synchronized (this.lock) {
            System.out.println("End of the day");
            this.enable = false;
            this.lock.notify();
        }
    }

    /**
     * Main method
     *
     * @param args args
     */
    public static void main(String[] args) {

        ProducerCustomer blockQueue = new ProducerCustomer();

        Thread producerOne = new Thread(new Runnable() {
            @Override
            public void run() {
                blockQueue.producerAddData();
            }
        });

        Thread producerTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                blockQueue.producerAddData();
            }
        });

        Thread customer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    blockQueue.customerCatchData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread endOfDay = new Thread(new Runnable() {
            @Override
            public void run() {
                blockQueue.endOfDay();
            }
        });

        customer.start();
        producerOne.start();
        producerTwo.start();
        endOfDay.start();
    }
}
