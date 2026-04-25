package com.taran.dsa.dynamicprogramming;

import java.util.Arrays;

/**
 * Problem 41: Coin Change
 * Given unlimited coins of different denominations and an amount,
 * return minimum number of coins needed to make the amount.
 * Return -1 if amount cannot be made.
 * LeetCode 322 | Dynamic Programming
 * Time: O(amount * n) where n is number of coin types | Space: O(amount)
 * TODO: Implement solution.
 */
public class Problem41 {

    public static void main(String[] args) {
        int[] coins1 = {1, 2, 5};
        int amount1 = 5;

        int[] coins2 = {2};
        int amount2 = 3;

        int[] coins3 = {10};
        int amount3 = 1;

        int[] coins4 = {1, 2, 5};
        int amount4 = 0;

        System.out.println("Sample input 1: coins = " + Arrays.toString(coins1) + ", amount = " + amount1);
        System.out.println("Minimum coins needed: " + minCoins(coins1, amount1));
        // Expected output: 2 (2 + 2 + 1)

        System.out.println("\nSample input 2: coins = " + Arrays.toString(coins2) + ", amount = " + amount2);
        System.out.println("Minimum coins needed: " + minCoins(coins2, amount2));
        // Expected output: -1 (cannot make 3 with only 2s)

        System.out.println("\nSample input 3: coins = " + Arrays.toString(coins3) + ", amount = " + amount3);
        System.out.println("Minimum coins needed: " + minCoins(coins3, amount3));
        // Expected output: -1 (cannot make 1 with only 10s)

        System.out.println("\nSample input 4: coins = " + Arrays.toString(coins4) + ", amount = " + amount4);
        System.out.println("Minimum coins needed: " + minCoins(coins4, amount4));
        // Expected output: 0 (no coins needed for amount 0)
    }

    /**
     * Find minimum number of coins to make the amount
     * Hint: Use dp[i] = minimum coins to make amount i
     */
    public static int minCoins(int[] coins, int amount) {
        // TODO: Implement this method
        // Initialize dp array with infinity for all amounts except 0
        return -1;
    }
}
