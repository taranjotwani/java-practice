package com.taran.dsa.searchingsorting;

import java.util.Arrays;

/**
 * Problem 40: Kth Largest Element in an Array
 * Find the kth largest element in an unsorted array. Note: k = 1 means the largest.
 * LeetCode 215 | QuickSelect / Heap / Sorting
 * Time: O(n) average with QuickSelect, O(n log n) with sorting | Space: O(1)
 * TODO: Implement solution.
 */
public class Problem40 {

    public static void main(String[] args) {
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;

        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;

        int[] nums3 = {1};
        int k3 = 1;

        System.out.println("Sample input 1: " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Kth largest element: " + findKthLargest(nums1, k1));
        // Expected output: 5

        System.out.println("\nSample input 2: " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Kth largest element: " + findKthLargest(nums2, k2));
        // Expected output: 4

        System.out.println("\nSample input 3: " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Kth largest element: " + findKthLargest(nums3, k3));
        // Expected output: 1
    }

    /**
     * Find kth largest element
     * Hint: Can use QuickSelect (O(n) avg), Min-Heap (O(n log k)), or sorting (O(n log n))
     */
    public static int findKthLargest(int[] nums, int k) {
        // TODO: Implement this method
        // Approach 1: Sort and return nth element
        // Approach 2: Use Min-Heap of size k
        // Approach 3: Use QuickSelect algorithm
        return -1;
    }
}
