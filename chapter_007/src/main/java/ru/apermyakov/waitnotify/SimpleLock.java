package ru.apermyakov.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class for modulate simple lock.
 *
 * @author apermyakov
 * @version 1.0
 * @since 21.11.2017
 */
@ThreadSafe
public class SimpleLock {

    /**
     * Field for thread, that lock method.
     */
    @GuardedBy("threadLock")
    private Long lockerThread = -1L;

    /**
     * Field for lock.
     */
    @GuardedBy("itself")
    private final Object threadLock = new Object();

    /**
     * Method for lock method by some thread.
     */
    public void lock() {
        synchronized (threadLock) {
            if (lockerThread != Thread.currentThread().getId()) {
                while (lockerThread != -1L) {
                    try {
                        threadLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lockerThread = Thread.currentThread().getId();
            }
        }
    }

    /**
     * Method for unlock method by thread.
     */
    public void unlock() {
        synchronized (threadLock) {
            lockerThread = -1L;
            threadLock.notifyAll();
        }
    }

    /**
     * Main method.
     *
     * @param args args
     */
    public static void main(String[] args) {
        SimpleLock lock = new SimpleLock();

        Thread first = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();

                try {
                    System.out.println("1 Do smt with object");
                    Thread.sleep(1000);
                    System.out.println("1 Work done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();

                try {
                    System.out.println("2 Do smt with object");
                    Thread.sleep(1000);
                    System.out.println("2 Work done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread third = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();

                try {
                    System.out.println("3 Do smt with object");
                    lock.lock();
                    Thread.sleep(1000);
                    System.out.println("3 Work done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        first.start();
        second.start();
        third.start();
    }
}

