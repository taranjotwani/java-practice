package com.taran.dsa.threads;

/**
 * PROBLEM: Thread-Safe Caching using ConcurrentHashMap
 *
 * <p><b>Objective:</b> Build a thread-safe cache with hit/miss tracking.</p>
 *
 * <p><b>Context:</b> ConcurrentHashMap uses segment-level locking, allowing concurrent
 * reads and writes. computeIfAbsent() is atomic - useful for lazy initialization.</p>
 *
 * <p><b>Requirements:</b></p>
 * <ul>
 *   <li>Use ConcurrentHashMap to store cache entries</li>
 *   <li>Track cache hits and misses with AtomicInteger</li>
 *   <li>Implement get(key) - increments hit if found, miss if not</li>
 *   <li>Implement put(key, value) - stores in cache</li>
 *   <li>Implement computeIfAbsent(key) - computes value if absent (with 10ms sleep to simulate DB lookup)</li>
 *   <li>Create 5 threads, each accessing keys 0-4 repeatedly (20 times each)</li>
 *   <li>Print cache stats (size, hits, misses)</li>
 * </ul>
 *
 * <p><b>Key Learning Points:</b></p>
 * <ul>
 *   <li>ConcurrentHashMap vs HashMap in multi-threaded scenarios</li>
 *   <li>computeIfAbsent() for atomic lazy initialization</li>
 *   <li>Cache hit/miss ratio calculation</li>
 * </ul>
 */
public class ConcurrentCacheExample {
    // TODO: Declare ConcurrentHashMap cache
    // TODO: Declare AtomicInteger hitCount
    // TODO: Declare AtomicInteger missCount

    /**
     * Retrieves a value from cache. Increments hit count if found, miss count if not.
     *
     * @param key the cache key
     * @return the cached value or null if not found
     */
    public String get(String key) {
        // TODO: Implement
        // Get value from cache
        // If found: increment hitCount and return value
        // If not found: increment missCount and return null
        return null;
    }

    /**
     * Puts a value in the cache.
     *
     * @param key   the cache key
     * @param value the value to cache
     */
    public void put(String key, String value) {
        // TODO: Implement
    }

    /**
     * Atomically computes a value if key is absent.
     * Simulates expensive database lookup (10ms).
     *
     * @param key the cache key
     * @return the cached value (computed or existing)
     */
    public String computeIfAbsent(String key) {
        // TODO: Use cache.computeIfAbsent()
        // Lambda should:
        //   - Sleep for 10ms (simulate DB lookup)
        //   - Return "Value-for-" + key
        return null;
    }

    /**
     * Prints cache statistics.
     */
    public void printStats() {
        // TODO: Print cache size, hits, misses, and hit ratio
    }

    /**
     * MAIN METHOD: Demonstrate concurrent cache access.
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO: Implement
        // 1. Create ConcurrentCacheExample instance
        // 2. Create 5 threads
        // 3. Each thread calls computeIfAbsent() 20 times for keys 0-4 (rotating)
        // 4. Print thread name and key accessed
        // 5. Wait for all threads to complete
        // 6. Print cache statistics
    }
}
