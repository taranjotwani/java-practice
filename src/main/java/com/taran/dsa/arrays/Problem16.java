package com.taran.dsa.arrays;

import java.util.Arrays;

/**
 * Problem 16: Search Insert Position
 * Given a sorted array and a target value, return the index if found,
 * otherwise return the index where it would be if it were inserted in order.
 * LeetCode 35 | Binary Search
 * Time: O(log n) | Space: O(1)
 * TODO: Implement solution.
 */
public class Problem16 {

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 6};
        int target1 = 5;

        int[] nums2 = {1, 3, 5, 6};
        int target2 = 2;

        int[] nums3 = {1, 3, 5, 6};
        int target3 = 7;

        System.out.println("Sample input 1: " + Arrays.toString(nums1) + ", target = " + target1);
        System.out.println("Insert position: " + searchInsertPosition(nums1, target1));
        // Expected output: 2

        System.out.println("\nSample input 2: " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Insert position: " + searchInsertPosition(nums2, target2));
        // Expected output: 1

        System.out.println("\nSample input 3: " + Arrays.toString(nums3) + ", target = " + target3);
        System.out.println("Insert position: " + searchInsertPosition(nums3, target3));
        // Expected output: 4
    }

    /**
     * Find the index to insert target in sorted array
     */
    public static int searchInsertPosition(int[] nums, int target) {
        // TODO: Implement this method using binary search
        return -1;
    }
}
