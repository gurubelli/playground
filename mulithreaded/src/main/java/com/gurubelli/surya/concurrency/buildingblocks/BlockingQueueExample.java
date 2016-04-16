package com.gurubelli.surya.concurrency.buildingblocks;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by esrigur on 4/14/2016.
 */
public class BlockingQueueExample {

    public static void main(String[] args) {

        System.out.println(" Hello welcome to Programming");
        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(5);
        BlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();

        new Thread(new Producer(linkedBlockingQueue), "Producer").start();
        new Thread(new Consumer(linkedBlockingQueue), "Producer").start();


    }


}

class Producer implements Runnable {

    BlockingQueue<String> linkedBlockingQueue = null;

    public Producer(BlockingQueue<String> linkedBlockingQueue) {
        this.linkedBlockingQueue = linkedBlockingQueue;
        Thread.currentThread().setName(this.getClass().getName());
    }

    public void run() {
        System.out.println("Producer thread name " + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            System.out.println(" Producing an element " + String.valueOf(i));
            try {
                linkedBlockingQueue.put(String.valueOf(i));
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

class Consumer implements Runnable {

    BlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();

    public Consumer(BlockingQueue<String> linkedBlockingQueue) {
        this.linkedBlockingQueue = linkedBlockingQueue;
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Consuming an element " + linkedBlockingQueue.take());
            } catch (InterruptedException ex) {
                System.out.println("Thread has been interrupted");
                Thread.currentThread().interrupt();
            }
        }
    }
}
