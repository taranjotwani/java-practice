package com.taran.dsa.arrays;

import java.util.Arrays;

/**
 * Problem 02: Find the second largest element in an array.
 * TODO: Implement solution.
 */
public class Problem02 {

    public static void main(String[] args) {
        int[] nums = {12, 7, 19, 4, 19, 8};
        Arrays.sort(nums);
        int secondLargest = nums[nums.length - 2];
        System.out.println("Second largest value in the array: " + secondLargest);
        System.out.println("Sample input: " + java.util.Arrays.toString(nums));
    }
}
