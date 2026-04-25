package com.taran.dsa.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * PROBLEM: Resource Pool Management using Semaphore
 *
 * <p><b>Objective:</b> Limit concurrent access to a limited resource pool using Semaphore.</p>
 *
 * <p><b>Context:</b> Semaphore maintains permits. Threads acquire permits before accessing
 * resources and release them after. If no permits are available, the thread blocks.
 * Perfect for managing connection pools or thread pools.</p>
 *
 * <p><b>Requirements:</b></p>
 * <ul>
 *   <li>Create DatabaseConnection class simulating query execution (1 second)</li>
 *   <li>Create Semaphore with 3 permits (maxConnections)</li>
 *   <li>Create 3 DatabaseConnection instances</li>
 *   <li>Create QueryTask class implementing Runnable:
 *       - Acquire semaphore permit
 *       - Execute query on a connection (rotating)
 *       - Release semaphore permit in finally
 *   </li>
 *   <li>Create ExecutorService with 10 threads</li>
 *   <li>Submit 10 QueryTasks to execute</li>
 *   <li>Observe: only 3 queries run concurrently despite 10 threads</li>
 * </ul>
 *
 * <p><b>Key Learning Points:</b></p>
 * <ul>
 *   <li>Semaphore permits and acquire/release</li>
 *   <li>Binary vs counting semaphores</li>
 *   <li>Bounding concurrent resource access</li>
 * </ul>
 */
public class SemaphoreExample {
    private static class DatabaseConnection {
        private final int connectionId;

        public DatabaseConnection(int connectionId) {
            this.connectionId = connectionId;
        }

        public void executeQuery(String query) throws InterruptedException {
            // TODO: Implement
            // 1. Print "Connection-{connectionId} executing: {query}"
            // 2. Sleep for 1 second (simulate query execution)
            // 3. Print "Connection-{connectionId} completed query"
        }
    }

    /**
     * Simulates a task that needs database access.
     */
    private static class QueryTask implements Runnable {
        // TODO: Add taskId, semaphore, connections fields

        public QueryTask(int taskId, Semaphore semaphore, DatabaseConnection[] connections) {
            // TODO: Initialize fields
        }

        @Override
        public void run() {
            try {
                // TODO: Implement
                // 1. Print "Task-{taskId} waiting for a connection..."
                // 2. Call semaphore.acquire() to get a permit (blocks if none available)
                // 3. Print "Task-{taskId} acquired a connection"
                // 4. Calculate connectionIndex = taskId % connections.length
                // 5. Call connection.executeQuery("SELECT * FROM users WHERE id={taskId}")
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                // TODO: Release the semaphore permit
                // TODO: Print "Task-{taskId} released the connection"
            }
        }
    }

    /**
     * MAIN METHOD: Demonstrate a connection pool with limited capacity.
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO: Implement
        // 1. Define maxConnections = 3
        // 2. Define numTasks = 10
        // 3. Create Semaphore with maxConnections permits
        // 4. Create array of 3 DatabaseConnection objects
        // 5. Create ExecutorService with numTasks threads
        // 6. Print message about starting tasks
        // 7. For each task, execute new QueryTask
        // 8. Shutdown executor and await termination
        // 9. Print "All tasks completed!"
    }
}
