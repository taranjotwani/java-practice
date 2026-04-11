package com.taran.dsa.arrays;

import java.util.Arrays;

/**
 * Problem 10: Find the top K largest elements in an array.
 * TODO: Implement solution.
 */
public class Problem10 {

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 1;
        int[] largestElements = new int[k];
        Arrays.sort(nums);
        for(int i = 0; i < k; i++) {
            largestElements[i] = nums[nums.length - 1 - i];
        }


        System.out.println("Sample input: nums=" + java.util.Arrays.toString(nums) + ", k=" + k + " (after sorting)");
        System.out.println("Top " + k + " largest elements in the array: " + java.util.Arrays.toString(largestElements));
        // Expected output example: [6, 5]
    }
}
