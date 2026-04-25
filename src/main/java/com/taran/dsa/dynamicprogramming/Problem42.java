package com.taran.dsa.dynamicprogramming;

import java.util.Arrays;

/**
 * Problem 42: Longest Increasing Subsequence (LIS)
 * Find the length of the longest subsequence where elements are in strictly increasing order.
 * LeetCode 300 | Dynamic Programming / Binary Search
 * Time: O(n log n) with binary search | Space: O(n)
 * TODO: Implement solution.
 */
public class Problem42 {

    public static void main(String[] args) {
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums2 = {0, 1, 0, 4, 4, 4, 3, 5, 5};
        int[] nums3 = {1, 2, 3, 4, 5};
        int[] nums4 = {5, 4, 3, 2, 1};

        System.out.println("Sample input 1: " + Arrays.toString(nums1));
        System.out.println("Length of LIS: " + lengthOfLIS(nums1));
        // Expected output: 4 (subsequence: [2, 3, 7, 101])

        System.out.println("\nSample input 2: " + Arrays.toString(nums2));
        System.out.println("Length of LIS: " + lengthOfLIS(nums2));
        // Expected output: 4 (subsequence: [0, 1, 4, 5])

        System.out.println("\nSample input 3: " + Arrays.toString(nums3));
        System.out.println("Length of LIS: " + lengthOfLIS(nums3));
        // Expected output: 5

        System.out.println("\nSample input 4: " + Arrays.toString(nums4));
        System.out.println("Length of LIS: " + lengthOfLIS(nums4));
        // Expected output: 1
    }

    /**
     * Find length of longest increasing subsequence
     * Hint: dp[i] = length of LIS ending at index i
     */
    public static int lengthOfLIS(int[] nums) {
        // TODO: Implement this method
        // Approach 1: O(n^2) DP or
        // Approach 2: O(n log n) with binary search
        return 0;
    }
}
