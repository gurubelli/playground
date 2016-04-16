package com.gurubelli.surya.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by esrigur on 4/14/2016.
 */
public class AtomicIntegerSample {

    private final AtomicInteger successFullSyncCount = new AtomicInteger(0);
    private final AtomicLong overallSyncTimeTaken  = new AtomicLong(0);

    public void incrementSuccessFullSyncCount() {
        this.successFullSyncCount.incrementAndGet();
    }

    public void addOverallSyncTimeTaken(final Long syncTimeTaken) {
        overallSyncTimeTaken.addAndGet(syncTimeTaken);
    }

    /**
     * Can be called by multiple threads
     * @return
     */
    public long getAverageSuccessfulSyncTimeTaken() {
        if (successFullSyncCount.get() == 0) {
            return 0;
        } else {
            return overallSyncTimeTaken.get() / successFullSyncCount.get();
        }
    }

    public static void main(String [] args) {

        AtomicIntegerSample atomicIntegerSample = new AtomicIntegerSample();
        ThreadOne myThread1 = new ThreadOne(atomicIntegerSample);
        ThreadTwo myThread2 = new ThreadTwo(atomicIntegerSample);
        new Thread(myThread1).start();
        new Thread(myThread2).start();
    }

}

class ThreadOne implements Runnable {
     AtomicIntegerSample atomicIntegerSample = null;

    public ThreadOne(AtomicIntegerSample atomicIntegerSample) {
        this.atomicIntegerSample = atomicIntegerSample;
    }
    public void run() {
        System.out.println("In thread " + Thread.currentThread().getName());
        atomicIntegerSample.incrementSuccessFullSyncCount();
        atomicIntegerSample.addOverallSyncTimeTaken(1000L);
    }
}

class ThreadTwo implements Runnable {
    AtomicIntegerSample atomicIntegerSample = null;

    public ThreadTwo(AtomicIntegerSample atomicIntegerSample) {
        this.atomicIntegerSample = atomicIntegerSample;
    }
    public void run() {
        System.out.println("In thread " + Thread.currentThread().getName());
        System.out.println("Average SuccessfulSync time " +  atomicIntegerSample.getAverageSuccessfulSyncTimeTaken());
        atomicIntegerSample.incrementSuccessFullSyncCount();
    }
}
