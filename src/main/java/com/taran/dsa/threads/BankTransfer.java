package com.taran.dsa.threads;

/**
 * PROBLEM: Thread-Safe Bank Transfer using ReentrantLock
 *
 * <p><b>Objective:</b> Implement atomic money transfers between accounts without deadlock.</p>
 *
 * <p><b>Context:</b> Transferring money requires atomicity - both debit and credit must succeed
 * together. ReentrantLock provides finer control than synchronized. The key is acquiring locks
 * in a consistent order to prevent deadlock.</p>
 *
 * <p><b>Requirements:</b></p>
 * <ul>
 *   <li>Create Account class with id, balance, and ReentrantLock</li>
 *   <li>Implement transfer() - transfers money between accounts atomically</li>
 *   <li>CRITICAL: Acquire locks in order of account IDs to prevent deadlock</li>
 *   <li>Create 3 accounts: Account-1 ($1000), Account-2 ($500), Account-3 ($750)</li>
 *   <li>Create 3 threads transferring between different account pairs concurrently</li>
 *   <li>Each thread performs 5 transfers of varying amounts</li>
 *   <li>Verify total money is conserved ($2250)</li>
 * </ul>
 *
 * <p><b>Key Learning Points:</b></p>
 * <ul>
 *   <li>Deadlock prevention through lock ordering</li>
 *   <li>ReentrantLock vs synchronized blocks</li>
 *   <li>Atomic multi-step operations</li>
 * </ul>
 */
public class BankTransfer {
    private static class Account {
        private final long id;
        private double balance;
        // TODO: Add ReentrantLock

        public Account(long id, double initialBalance) {
            this.id = id;
            this.balance = initialBalance;
            // TODO: Initialize lock
        }

        /**
         * Transfers money from this account to the target account.
         * Must acquire locks in order of account IDs to prevent deadlock.
         *
         * @param target the account to transfer to
         * @param amount the amount to transfer
         * @return true if transfer succeeded, false if insufficient funds
         */
        public boolean transfer(Account target, double amount) {
            // TODO: Implement
            // 1. Determine lock order (lower ID first)
            // 2. Acquire locks in that order
            // 3. Check if this.balance >= amount
            // 4. Deduct from this.balance, add to target.balance
            // 5. Print success message
            // 6. Unlock both (use try-finally)
            return false;
        }

        /**
         * Gets the current balance (thread-safe).
         */
        public double getBalance() {
            // TODO: Acquire lock, return balance, release lock (use try-finally)
            return 0;
        }

        /**
         * Prints this account's balance.
         */
        public void printBalance() {
            // TODO: Print "Account-{id}: ${balance}" formatted to 2 decimal places
        }
    }

    /**
     * MAIN METHOD: Demonstrate concurrent transfers between accounts.
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO: Implement
        // 1. Create 3 accounts:
        //    - Account-1 with $1000
        //    - Account-2 with $500
        //    - Account-3 with $750
        // 2. Print initial balances
        // 3. Create 3 threads:
        //    - Thread 1: transfers $50 from Account-1 to Account-2 (5 times)
        //    - Thread 2: transfers $30 from Account-2 to Account-3 (5 times)
        //    - Thread 3: transfers $25 from Account-3 to Account-1 (5 times)
        // 4. Start and join all threads
        // 5. Print final balances
        // 6. Calculate and verify total (should be $2250)
    }
}
