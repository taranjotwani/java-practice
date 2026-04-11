package com.taran.dsa.arrays;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Problem 06: Find the missing number in an array from 1 to N.
 * TODO: Implement solution.
 */
public class Problem06 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 6};
        int n = 6;
        System.out.println("Sample input: nums=" + java.util.Arrays.toString(nums) + ", n=" + n);
        int arraySum  = Arrays.stream(nums).sum();
        int expectedSum = IntStream.rangeClosed(1,n).sum();
        int missingNumber = expectedSum - arraySum;
        System.out.println("Missing number in the array: " + missingNumber);
    }
}
