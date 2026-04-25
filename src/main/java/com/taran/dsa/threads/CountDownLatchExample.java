package com.taran.dsa.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * PROBLEM: Parallel Task Coordination using CountDownLatch
 *
 * <p><b>Objective:</b> Use CountDownLatch to wait for multiple tasks to complete.</p>
 *
 * <p><b>Context:</b> CountDownLatch is initialized with a count. Each task decrements
 * the count when it finishes. The main thread waits (blocks) until count reaches zero.
 * Unlike Thread.join(), you control when the count decreases.</p>
 *
 * <p><b>Requirements:</b></p>
 * <ul>
 *   <li>Create a CountDownLatch with 5 tasks</li>
 *   <li>Create Task class implementing Runnable</li>
 *   <li>Each task:
 *       - Prints "Task-{id} started"
 *       - Sleeps for random duration (500-2500ms)
 *       - Prints "Task-{id} completed"
 *       - Calls latch.countDown() in finally block
 *   </li>
 *   <li>Use ExecutorService with 3 threads for 5 tasks</li>
 *   <li>Main thread calls latch.await() to wait for all tasks</li>
 *   <li>Print total duration and "All tasks completed"</li>
 * </ul>
 *
 * <p><b>Key Learning Points:</b></p>
 * <ul>
 *   <li>CountDownLatch one-way synchronization</li>
 *   <li>Difference between CountDownLatch and Thread.join()</li>
 *   <li>ExecutorService thread pool basics</li>
 * </ul>
 */
public class CountDownLatchExample {

    /**
     * Simulates a task that takes variable time to complete.
     */
    private static class Task implements Runnable {
        // TODO: Add taskId and latch fields

        public Task(int taskId, CountDownLatch latch) {
            // TODO: Initialize fields
        }

        @Override
        public void run() {
            try {
                // TODO: Implement
                // 1. Print "Task-{taskId} started"
                // 2. Sleep for random duration (500-2500ms): (long)(Math.random() * 2000) + 500
                // 3. Print "Task-{taskId} completed"
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                // TODO: Call latch.countDown() to decrement count
            }
        }
    }

    /**
     * MAIN METHOD: Demonstrate waiting for multiple tasks to complete.
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO: Implement
        // 1. Define numTasks = 5
        // 2. Create CountDownLatch with numTasks
        // 3. Create ExecutorService with 3 threads (newFixedThreadPool(3))
        // 4. Record start time
        // 5. For each task, execute new Task(i, latch) using executor
        // 6. Print "Main thread waiting for all tasks..."
        // 7. Call latch.await() to block until all tasks complete
        // 8. Calculate and print duration
        // 9. Shutdown executor
    }
}
