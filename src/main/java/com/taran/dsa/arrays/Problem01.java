package com.taran.dsa.arrays;

import java.util.Arrays;

/**
 * Problem 01: Find the maximum element in an array.
 * TODO: Implement solution.
 */
public class Problem01 {

    public static void main(String[] args) {
        int[] nums = {3, 9, 2, 15, 7};
        Integer maxValue = Arrays.stream(nums).max().getAsInt();


        System.out.println("Sample input: " + java.util.Arrays.toString(nums));
        System.out.println("Maximum value in the array: " + maxValue);        
        // Expected output example: 15
    }
}
