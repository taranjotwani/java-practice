package com.taran.dsa.arrays;

import java.util.Arrays;

/**
 * Problem 15: Majority Element
 * Find the element that appears more than n/2 times in the array (guaranteed to exist).
 * LeetCode 169 | Boyer-Moore Voting Algorithm
 * Time: O(n) | Space: O(1)
 * TODO: Implement solution.
 */
public class Problem15 {

    public static void main(String[] args) {
        int[] nums1 = {3, 2, 3};
        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
        int[] nums3 = {1};

        System.out.println("Sample input 1: " + Arrays.toString(nums1));
        System.out.println("Majority element: " + findMajority(nums1));
        // Expected output: 3

        System.out.println("\nSample input 2: " + Arrays.toString(nums2));
        System.out.println("Majority element: " + findMajority(nums2));
        // Expected output: 2

        System.out.println("\nSample input 3: " + Arrays.toString(nums3));
        System.out.println("Majority element: " + findMajority(nums3));
        // Expected output: 1
    }

    /**
     * Find the majority element using Boyer-Moore voting algorithm
     */
    public static int findMajority(int[] nums) {
        // TODO: Implement this method
        // Hint: Keep track of a candidate and its count
        // If count becomes 0, pick a new candidate
        return -1;
    }
}
