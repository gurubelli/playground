package com.gurubelli.surya.concurrency.buildingblocks;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by esrigur on 4/14/2016.
 */
public class SynchronousQueueExample {

    /**
     * It is really not a queue at all. It maintains no storage space for
     * queued elements.
     *
     * Synchronous queues are generally suitable only when there are enough consumers that there
     * nearly always will be on ready to take the hand off
     *
     * peek is not possible, as there is no element iteration is also not possible
     * insert
     */
    public static void main(String [] args) {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
        new Thread(new Producer2(synchronousQueue), "Producer").start();
        new Thread(new Consumer2(synchronousQueue), "Consumer").start();
    }


}

class Producer2 implements Runnable {

    SynchronousQueue<String> synchronousQueue = null;

    public Producer2(SynchronousQueue<String> synchronousQueue) {
        this.synchronousQueue = synchronousQueue;
        Thread.currentThread().setName(this.getClass().getName());
    }

    public void run() {
        System.out.println("Producer thread name " + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            System.out.println(" Producing an element " + String.valueOf(i));
            try {
                synchronousQueue.put(String.valueOf(i)); //put call to SynchronousQueue will not return until there is corresponding take call
                System.out.println("After producing ");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                //Dealing with Interuppted Exception
                //You can not rethrow it over here as
                System.out.println(" Thread " + Thread.currentThread().getId());
                //Restore the interrupted status
                Thread.currentThread().interrupt();
            }
        }

    }
}

class Consumer2 implements Runnable {

    SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

    public Consumer2(SynchronousQueue<String> synchronousQueue) {
        this.synchronousQueue = synchronousQueue;
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Consuming an element " + synchronousQueue.take());
            } catch (InterruptedException ex) {
                System.out.println("Thread has been interrupted");
                Thread.currentThread().interrupt();
            }
        }
    }
}
