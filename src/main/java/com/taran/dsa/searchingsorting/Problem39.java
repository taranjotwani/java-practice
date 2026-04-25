package com.taran.dsa.searchingsorting;

import java.util.Arrays;

/**
 * Problem 39: Find Minimum in Rotated Sorted Array
 * A sorted array is rotated. Find the minimum element.
 * Assume no duplicates exist.
 * LeetCode 153 | Binary Search
 * Time: O(log n) | Space: O(1)
 * TODO: Implement solution.
 */
public class Problem39 {

    public static void main(String[] args) {
        int[] nums1 = {3, 4, 5, 1, 2};
        int[] nums2 = {2, 1};
        int[] nums3 = {1, 2};
        int[] nums4 = {3, 1, 2};

        System.out.println("Sample input 1: " + Arrays.toString(nums1));
        System.out.println("Minimum element: " + findMin(nums1));
        // Expected output: 1

        System.out.println("\nSample input 2: " + Arrays.toString(nums2));
        System.out.println("Minimum element: " + findMin(nums2));
        // Expected output: 1

        System.out.println("\nSample input 3: " + Arrays.toString(nums3));
        System.out.println("Minimum element: " + findMin(nums3));
        // Expected output: 1

        System.out.println("\nSample input 4: " + Arrays.toString(nums4));
        System.out.println("Minimum element: " + findMin(nums4));
        // Expected output: 1
    }

    /**
     * Find minimum in rotated sorted array
     * Hint: Use binary search. Compare mid with right to determine which half contains the minimum.
     */
    public static int findMin(int[] nums) {
        // TODO: Implement this method using binary search
        return -1;
    }
}
