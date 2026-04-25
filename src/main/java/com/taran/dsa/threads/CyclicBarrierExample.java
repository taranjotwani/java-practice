package com.taran.dsa.threads;

import java.util.concurrent.CyclicBarrier;

/**
 * PROBLEM: Synchronized Multi-Phase Tasks using CyclicBarrier
 *
 * <p><b>Objective:</b> Coordinate multi-phase tasks where each phase must complete before proceeding.</p>
 *
 * <p><b>Context:</b> CyclicBarrier synchronizes threads at a common point. All threads must
 * reach the barrier before any can proceed. Unlike CountDownLatch, it's reusable for multiple rounds.</p>
 *
 * <p><b>Requirements:</b></p>
 * <ul>
 *   <li>Create Matrix class with random data and sum calculation</li>
 *   <li>Create 2 CyclicBarrier instances (phase1, phase2) with 4 threads each</li>
 *   <li>Create MatrixProcessor implementing Runnable:
 *       - Phase 1: fillRandom() and wait at phase1Barrier
 *       - Phase 2: processMatrix (500ms sleep) and wait at phase2Barrier
 *   </li>
 *   <li>Create ExecutorService with 4 threads</li>
 *   <li>Submit 4 MatrixProcessor tasks with 100x100 matrices</li>
 *   <li>Each barrier prints ">>> All threads reached barrier, proceeding to next phase <<<"</li>
 *   <li>Observe phases executing sequentially across all threads</li>
 * </ul>
 *
 * <p><b>Key Learning Points:</b></p>
 * <ul>
 *   <li>CyclicBarrier multi-phase synchronization</li>
 *   <li>Barrier actions executed when all threads arrive</li>
 *   <li>Reusable nature (unlike CountDownLatch)</li>
 * </ul>
 */
public class CyclicBarrierExample {
    private static class Matrix {
        private final int[][] data;

        public Matrix(int rows, int cols) {
            this.data = new int[rows][cols];
        }

        public void fillRandom() {
            // TODO: Fill matrix with random values 0-99
        }

        public int getSum() {
            // TODO: Calculate and return sum of all elements
            return 0;
        }

        public void printMatrix() {
            // TODO: Print first 3x3 of matrix
            System.out.println("Matrix:");
            for (int i = 0; i < Math.min(3, data.length); i++) {
                for (int j = 0; j < Math.min(3, data[0].length); j++) {
                    System.out.print(data[i][j] + " ");
                }
                System.out.println("...");
            }
        }
    }

    /**
     * A task that processes a portion of the matrix.
     * Waits at barriers to sync with other tasks.
     */
    private static class MatrixProcessor implements Runnable {
        // TODO: Add threadId, matrix, phase1Barrier, phase2Barrier fields

        public MatrixProcessor(int threadId, Matrix matrix,
                             CyclicBarrier phase1Barrier, CyclicBarrier phase2Barrier) {
            // TODO: Initialize fields
        }

        @Override
        public void run() {
            try {
                // Phase 1: Initialize data
                // TODO: Print "Thread-{threadId} starting Phase 1"
                // TODO: Call matrix.fillRandom()
                // TODO: Print "Thread-{threadId} completed Phase 1"
                // TODO: Call phase1Barrier.await() to wait for all threads

                // Phase 2: Process data
                // TODO: Print "Thread-{threadId} starting Phase 2"
                // TODO: Sleep 500ms (simulate processing)
                // TODO: Print "Thread-{threadId} completed Phase 2"
                // TODO: Call phase2Barrier.await() to wait for all threads

                // TODO: Print "Thread-{threadId} finished!"
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * MAIN METHOD: Demonstrate barrier synchronization across multiple phases.
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO: Implement
        // 1. Define numThreads = 4, matrixSize = 100
        // 2. Create Runnable barrierAction that prints:
        //    ">>> All threads reached barrier, proceeding to next phase <<<"
        // 3. Create CyclicBarrier phase1Barrier with numThreads and barrierAction
        // 4. Create CyclicBarrier phase2Barrier with numThreads and barrierAction
        // 5. Create ExecutorService with numThreads
        // 6. Print starting message
        // 7. For each thread:
        //    - Create Matrix(matrixSize, matrixSize)
        //    - Execute MatrixProcessor(i, matrix, phase1Barrier, phase2Barrier)
        // 8. Shutdown executor and await termination
        // 9. Print "All phases completed!"
    }
}
