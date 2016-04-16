package com.gurubelli.surya.concurrency.buildingblocks;

import java.util.concurrent.CountDownLatch;

/**
 * Created by esrigur on 4/15/2016.
 */
public class Synchronizer {

    /**
     * That coordinates the flow of threads based on its state.
     * They encapsulate the state that determines whether threads arriving at the synchronizer
     * should be allowed to pass or forced to wait
     */
    public static void main(String [] args) {

        /**
         * Uses
         * Ensuring a computation does not proceed until resource it needs have been initialized
         * Ensuring a service does not start until other service on which it depends have started
         */
    }
}

/**
 * Example for countdown latch --> It allows one or more threads to wait for a set of events to occur
 *
 */
class TestHarness {

    public long timeTasks(final int nThreads, final Runnable task) throws InterruptedException {

        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i=0 ; i < nThreads ; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }

                    } catch (InterruptedException ignored) {

                    }
                }
            };
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }

}