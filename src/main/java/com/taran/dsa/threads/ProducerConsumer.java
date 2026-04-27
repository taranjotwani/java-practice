package com.taran.dsa.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * PROBLEM: Producer-Consumer Pattern using BlockingQueue
 *
 * <p><b>Objective:</b> Implement the classic producer-consumer pattern with BlockingQueue.</p>
 *
 * <p><b>Context:</b> BlockingQueue automatically handles synchronization between producers
 * and consumers. Producers block when the queue is full, and consumers block when it's empty.
 * No explicit synchronization code needed!</p>
 *
 * <p><b>Requirements:</b></p>
 * <ul>
 *   <li>Create a BlockingQueue with capacity 5</li>
 *   <li>Implement produce(String item) using queue.put()</li>
 *   <li>Implement consume() using queue.take()</li>
 *   <li>Create 2 producer threads, each adding 5 items (with 100-150ms delays)</li>
 *   <li>Create 2 consumer threads, each removing 5 items (with 180-200ms delays)</li>
 *   <li>Print when items are produced and consumed</li>
 *   <li>Observe blocking behavior when queue is full/empty</li>
 * </ul>
 *
 * <p><b>Key Learning Points:</b></p>
 * <ul>
 *   <li>Blocking operations (put, take)</li>
 *   <li>How queue capacity affects producer blocking</li>
 *   <li>Thread coordination without explicit locks</li>
 * </ul>
 */
public class ProducerConsumer {
    // TODO: Declare BlockingQueue
    BlockingQueue<Integer> queue;

    public ProducerConsumer(int capacity) {
        queue = new ArrayBlockingQueue<>(capacity);
    }

    /**
     * Producer: Adds items to the queue.
     * Blocks if queue is full.
     *
     * @param item the item to produce
     * @throws InterruptedException if thread is interrupted
     */
    public void produce(String item) throws InterruptedException {
        queue.put(Integer.parseInt(item));
        // Print "Produced: " + item after adding to queue 
        System.out.println("Produced: " + item);
    }

    /**
     * Consumer: Removes and returns an item from the queue.
     * Blocks if queue is empty.
     *
     * @return the consumed item
     * @throws InterruptedException if thread is interrupted
     */
    public String consume() throws InterruptedException {
        int item = queue.take();
        // Print "Consumed: " + item after removing from queue
        System.out.println("Consumed: " + item);
        return null;
    }

    /**
     * MAIN METHOD: Demonstrate producers and consumers working concurrently.
     * Expected: You should see blocking when queue reaches capacity or is empty.
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO: Implement
        // 1. Create ProducerConsumer with capacity 5
        ProducerConsumer pc = new ProducerConsumer(5);
        // 2. Create producer1: produces 5 items with 100ms delay
        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.submit(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    pc.produce(String.valueOf(i));
                    Thread.sleep(100); // 100ms delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        // 3. Create producer2: produces 5 items (indices 5-9) with 150ms delay
        executor.submit(() -> {
            try {
                for (int i = 5; i < 9; i++) {
                    pc.produce(String.valueOf(i));
                    Thread.sleep(100); // 100ms delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        // 4. Create consumer1: consumes 5 items with 200ms delay
        executor.submit(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    pc.consume();
                    Thread.sleep(200); // 200ms delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        // 5. Create consumer2: consumes 5 items with 180ms delay
        executor.submit(() -> {
            try {
                for (int i = 5; i < 9; i++) {
                    pc.consume();
                    Thread.sleep(180); // 180ms delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        // 6. Start all threads
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        // 7. Join all threads and print "Done!"
        System.out.println("Done!");
    }
}
