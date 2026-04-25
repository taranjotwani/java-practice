# Concurrency Problems - Learning Edition

This folder contains practical **skeleton implementations** of concurrency problems for you to complete and learn from. Each file has a detailed problem description, method signatures, and TODO placeholders where you need to implement the solution.

## How to Use

1. **Read the problem description** at the top of each file (in the Javadoc)
2. **Review the requirements** section (lists what needs to be implemented)
3. **Implement the TODOs** - follow the comments for each method
4. **Run the program** to see if your implementation works correctly
5. **Compare your solution** with the concepts explained in the problem

## Problems Overview

### 1. **ThreadSafeCounter.java**
- **Data Structure**: `AtomicInteger`
- **Concept**: Lock-free thread-safe counter using Compare-And-Swap (CAS) operations
- **What to Implement**:
  - `increment()`, `decrement()`, `get()`, `reset()` methods
  - Multi-threaded test with 10 threads incrementing 1000 times each
  - Expected: final count = 10,000

### 2. **ProducerConsumer.java**
- **Data Structure**: `BlockingQueue`
- **Concept**: Classic producer-consumer pattern with automatic synchronization
- **What to Implement**:
  - `produce(String item)` using `queue.put()`
  - `consume()` using `queue.take()`
  - 2 producers and 2 consumers with various delays
  - Observe blocking behavior when queue fills/empties

### 3. **ConcurrentCacheExample.java**
- **Data Structure**: `ConcurrentHashMap`
- **Concept**: Thread-safe caching with hit/miss tracking
- **What to Implement**:
  - `get()`, `put()`, `computeIfAbsent()` methods
  - Cache statistics tracking with `AtomicInteger`
  - 5 threads accessing same keys (observe cache hits)

### 4. **BankTransfer.java**
- **Data Structure**: `ReentrantLock`
- **Concept**: Atomic multi-step operations with deadlock prevention
- **What to Implement**:
  - `Account` inner class with locked transfers
  - `transfer()` method with ordered lock acquisition
  - 3 concurrent transfer threads between 3 accounts
  - Verify money is conserved ($2250)

### 5. **CountDownLatchExample.java**
- **Data Structure**: `CountDownLatch`
- **Concept**: One-way synchronization for N tasks
- **What to Implement**:
  - `Task` class that decrements latch in finally block
  - Main thread using `latch.await()` to wait for all tasks
  - Measure total time for 5 parallel tasks using ExecutorService

### 6. **SemaphoreExample.java**
- **Data Structure**: `Semaphore`
- **Concept**: Managing limited resource access (connection pool)
- **What to Implement**:
  - `acquire()` and `release()` permit management
  - `DatabaseConnection` class simulating queries
  - 10 tasks competing for 3 database connections
  - Observe queuing when permits are exhausted

### 7. **ReadWriteLockExample.java**
- **Data Structure**: `ReadWriteLock` (ReentrantReadWriteLock)
- **Concept**: Optimized for read-heavy workloads
- **What to Implement**:
  - `getUser()` with read lock (multiple concurrent readers)
  - `setUser()` with write lock (exclusive access)
  - 5 reader threads and 2 writer threads
  - Observe readers running concurrently, writers blocking

### 8. **CyclicBarrierExample.java**
- **Data Structure**: `CyclicBarrier`
- **Concept**: Reusable synchronization for multi-phase tasks
- **What to Implement**:
  - `Matrix` class with fillRandom() and getSum()
  - `MatrixProcessor` with Phase 1 and Phase 2
  - Barrier actions printed when all threads arrive
  - Observe sequential phase execution across threads

## Learning Path

**Recommended Order:**
1. **ThreadSafeCounter** - Simplest, just atomic operations
2. **ProducerConsumer** - Get comfortable with blocking operations
3. **ConcurrentCacheExample** - Learn computeIfAbsent() pattern
4. **CountDownLatchExample** - Understand task synchronization
5. **SemaphoreExample** - Resource pool management
6. **BankTransfer** - Most complex, requires deadlock prevention
7. **ReadWriteLockExample** - Specialized lock for specific use case
8. **CyclicBarrierExample** - Multi-phase coordination

## Concurrency Data Structure Comparison

| Structure | Use Case | Thread-Safe | Blocking | Reusable |
|-----------|----------|-------------|----------|----------|
| AtomicInteger | Simple counters, flags | Yes | No | Yes |
| ConcurrentHashMap | Read-heavy maps | Yes | No | Yes |
| BlockingQueue | Producer-consumer | Yes | Yes | Yes |
| ReentrantLock | Complex operations | Manual | Manual | Yes |
| ReadWriteLock | Read-heavy data | Manual | Manual | Yes |
| CountDownLatch | Wait for N tasks | Yes | Yes | **No** |
| CyclicBarrier | Multi-phase sync | Yes | Yes | **Yes** |
| Semaphore | Resource pool limit | Yes | Yes | Yes |

## Running Your Solutions

```bash
cd /opt/java-practice/my-app

# Compile all
mvn compile

# Run a specific problem
mvn exec:java -Dexec.mainClass="com.taran.dsa.threads.ThreadSafeCounter"
mvn exec:java -Dexec.mainClass="com.taran.dsa.threads.ProducerConsumer"
mvn exec:java -Dexec.mainClass="com.taran.dsa.threads.ConcurrentCacheExample"
mvn exec:java -Dexec.mainClass="com.taran.dsa.threads.BankTransfer"
mvn exec:java -Dexec.mainClass="com.taran.dsa.threads.CountDownLatchExample"
mvn exec:java -Dexec.mainClass="com.taran.dsa.threads.SemaphoreExample"
mvn exec:java -Dexec.mainClass="com.taran.dsa.threads.ReadWriteLockExample"
mvn exec:java -Dexec.mainClass="com.taran.dsa.threads.CyclicBarrierExample"
```

## Key Concepts to Learn

### Thread Safety
- **Atomic Operations**: No intermediate states visible to other threads
- **Visibility**: One thread's writes are visible to other threads  
- **Ordering**: Operations execute in predictable order
- **Mutual Exclusion**: Only one thread accesses critical section

### Deadlock Prevention
- **Lock Ordering**: Always acquire locks in the same order (see BankTransfer)
- **Timeouts**: Use timed locks to prevent indefinite blocking
- **Immutability**: Prefer immutable objects to avoid locks

### Performance Considerations
- **Contention**: How much threads compete for locks
- **Throughput**: Operations per unit time
- **Latency**: Time for a single operation
- **Scalability**: Performance as thread count increases
- **Lock Scope**: Minimize critical sections

### Common Patterns
- **Producer-Consumer**: Coordinate producers adding data, consumers processing
- **Work Stealing**: Load balancing in thread pools
- **Master-Worker**: One thread coordinates, others do work
- **Pipeline**: Data flows through processing stages



1. ThreadSafeCounter       (30 min)  ✓ Understand atomics
2. ProducerConsumer        (45 min)  ✓ Understand blocking queues
3. CountDownLatchExample   (45 min)  ✓ Understand task coordination
4. ConcurrentCacheExample  (60 min)  ✓ Understand computeIfAbsent
5. SemaphoreExample        (60 min)  ✓ Understand resource limits
6. ReadWriteLockExample    (60 min)  ✓ Understand read/write optimization
7. CyclicBarrierExample    (75 min)  ✓ Understand multi-phase sync
8. BankTransfer            (90 min)  ✓ Master deadlock prevention