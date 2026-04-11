package com.taran.dsa.arrays;

import java.util.stream.IntStream;

/**
 * Problem 07: Rotate an array by K positions.
 * TODO: Implement solution.
 */
public class Problem07 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        int[] newarr = new int[nums.length];
        IntStream.range(0, nums.length).forEach(index -> {
            // This is he formula to calculate the new index after rotation
            int newIndex = (index + k) % nums.length;
            newarr[newIndex] = nums[index];
        });


        System.out.println("Sample input: nums=" + java.util.Arrays.toString(nums) + ", k=" + k);
        System.out.println("Rotated array: " + java.util.Arrays.toString(newarr));
        // Expected output example: [5, 6, 7, 1, 2, 3, 4]
    }
}
