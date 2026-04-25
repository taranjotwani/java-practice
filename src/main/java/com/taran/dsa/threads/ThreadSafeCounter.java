package com.taran.dsa.threads;

/**
 * PROBLEM: Thread-Safe Counter using AtomicInteger
 *
 * <p><b>Objective:</b> Implement a thread-safe counter using AtomicInteger.</p>
 *
 * <p><b>Context:</b> AtomicInteger provides lock-free thread-safe operations using
 * Compare-And-Swap (CAS) operations at the CPU level. This is more efficient than
 * synchronized blocks for simple counters.</p>
 *
 * <p><b>Requirements:</b></p>
 * <ul>
 *   <li>Implement increment() - returns new value after incrementing</li>
 *   <li>Implement decrement() - returns new value after decrementing</li>
 *   <li>Implement get() - returns current value</li>
 *   <li>Implement reset() - sets counter to zero</li>
 *   <li>Test with 10 threads, each incrementing 1000 times</li>
 *   <li>Verify final count matches expected value (10,000)</li>
 * </ul>
 *
 * <p><b>Key Learning Points:</b></p>
 * <ul>
 *   <li>How AtomicInteger works under the hood</li>
 *   <li>Compare-And-Swap (CAS) operations</li>
 *   <li>Non-blocking vs blocking synchronization</li>
 * </ul>
 */
public class ThreadSafeCounter {
    // TODO: Declare AtomicInteger counter initialized to 0

    /**
     * Increments the counter and returns the new value.
     *
     * @return the new value after incrementing
     */
    public int increment() {
        // TODO: Implement
        return 0;
    }

    /**
     * Decrements the counter and returns the new value.
     *
     * @return the new value after decrementing
     */
    public int decrement() {
        // TODO: Implement
        return 0;
    }

    /**
     * Returns the current value without modification.
     *
     * @return current counter value
     */
    public int get() {
        // TODO: Implement
        return 0;
    }

    /**
     * Resets the counter to zero.
     */
    public void reset() {
        // TODO: Implement
    }

    /**
     * MAIN METHOD: Test the counter with multiple threads incrementing concurrently.
     * Expected: final count should equal 10 threads * 1000 increments = 10,000
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO: Implement
        // 1. Create a ThreadSafeCounter instance
        // 2. Create 10 threads
        // 3. Each thread increments 1000 times
        // 4. Wait for all threads to finish (use join())
        // 5. Print final count and verify it matches expected value
    }
}
