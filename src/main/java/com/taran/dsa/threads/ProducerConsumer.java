package com.taran.dsa.threads;

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

    public ProducerConsumer(int capacity) {
        // TODO: Initialize BlockingQueue with given capacity
    }

    /**
     * Producer: Adds items to the queue.
     * Blocks if queue is full.
     *
     * @param item the item to produce
     * @throws InterruptedException if thread is interrupted
     */
    public void produce(String item) throws InterruptedException {
        // TODO: Implement using queue.put()
        // Print "Produced: " + item
    }

    /**
     * Consumer: Removes and returns an item from the queue.
     * Blocks if queue is empty.
     *
     * @return the consumed item
     * @throws InterruptedException if thread is interrupted
     */
    public String consume() throws InterruptedException {
        // TODO: Implement using queue.take()
        // Print "Consumed: " + item before returning
        return null;
    }

    /**
     * MAIN METHOD: Demonstrate producers and consumers working concurrently.
     * Expected: You should see blocking when queue reaches capacity or is empty.
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO: Implement
        // 1. Create ProducerConsumer with capacity 5
        // 2. Create producer1: produces 5 items with 100ms delay
        // 3. Create producer2: produces 5 items (indices 5-9) with 150ms delay
        // 4. Create consumer1: consumes 5 items with 200ms delay
        // 5. Create consumer2: consumes 5 items with 180ms delay
        // 6. Start all threads
        // 7. Join all threads and print "Done!"
    }
}
