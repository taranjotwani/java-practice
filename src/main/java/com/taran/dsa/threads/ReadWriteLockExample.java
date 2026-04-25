package com.taran.dsa.threads;

/**
 * PROBLEM: Read-Heavy Data Access using ReadWriteLock
 *
 * <p><b>Objective:</b> Optimize concurrent access for read-heavy workloads using ReadWriteLock.</p>
 *
 * <p><b>Context:</b> ReadWriteLock allows multiple readers concurrently but grants
 * exclusive access to writers. This is much better than ReentrantLock when reads
 * far outnumber writes.</p>
 *
 * <p><b>Requirements:</b></p>
 * <ul>
 *   <li>Create UserCache class with HashMap and ReentrantReadWriteLock</li>
 *   <li>Implement getUser(userId) - read operation:
 *       - Acquire read lock
 *       - Print thread name + " reading user-{userId}"
 *       - Sleep 100ms (simulate read time)
 *       - Return value or "User not found"
 *       - Release read lock in finally
 *   </li>
 *   <li>Implement setUser(userId, name) - write operation:
 *       - Acquire write lock
 *       - Print thread name + " writing user-{userId}"
 *       - Sleep 200ms (simulate write time)
 *       - Put into map
 *       - Print thread name + " completed write"
 *       - Release write lock in finally
 *   </li>
 *   <li>Create ExecutorService with 10 threads</li>
 *   <li>Initialize 3 users</li>
 *   <li>Submit 5 reader threads (3 reads each)</li>
 *   <li>Submit 2 writer threads (1 write each)</li>
 *   <li>Observe: readers run concurrently; writers block readers</li>
 * </ul>
 *
 * <p><b>Key Learning Points:</b></p>
 * <ul>
 *   <li>ReadWriteLock vs ReentrantLock performance</li>
 *   <li>Multiple concurrent readers</li>
 *   <li>Exclusive writer access</li>
 * </ul>
 */
public class ReadWriteLockExample {
    private static class UserCache {
        // TODO: Add ReadWriteLock field
        // TODO: Add HashMap<Integer, String> users field

        /**
         * Read operation: Can be done concurrently by multiple threads.
         */
        public String getUser(int userId) {
            // TODO: Implement
            // 1. Acquire read lock
            // 2. Print: Thread.currentThread().getName() + " reading user-" + userId
            // 3. Sleep 100ms
            // 4. Get value from map (or "User not found")
            // 5. Release read lock in finally
            // 6. Return value
            return null;
        }

        /**
         * Write operation: Has exclusive access, blocks all readers and writers.
         */
        public void setUser(int userId, String name) {
            // TODO: Implement
            // 1. Acquire write lock
            // 2. Print: Thread.currentThread().getName() + " writing user-" + userId
            // 3. Sleep 200ms
            // 4. Put userId->name in map
            // 5. Print: Thread.currentThread().getName() + " completed write"
            // 6. Release write lock in finally
        }
    }

    /**
     * MAIN METHOD: Demonstrate concurrent reads and exclusive writes.
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO: Implement
        // 1. Create UserCache
        // 2. Create ExecutorService with 10 threads
        // 3. Initialize data: setUser(1,"Alice"), setUser(2,"Bob"), setUser(3,"Charlie")
        // 4. Print "Starting concurrent read and write operations:"
        // 5. Submit 5 reader threads:
        //    - Each reads 3 times (userId rotating 1-3)
        //    - 50ms sleep between reads
        // 6. Submit 2 writer threads:
        //    - Each writes 1 user (userId 4,5 with User-0, User-1)
        //    - 100ms sleep after write
        // 7. Shutdown executor and await termination
        // 8. Print "All operations completed!"
    }
}
