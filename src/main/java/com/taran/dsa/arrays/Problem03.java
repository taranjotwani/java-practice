package com.taran.dsa.arrays;

import java.util.Arrays;

/**
 * Problem 03: Reverse an array in-place.
 * TODO: Implement solution.
 */
public class Problem03 {

    public static void main(String[] args) {
        int[] nums = {112, 23, 37, 41, 54};
        int left = 0;
        int right = nums.length - 1;
         System.out.println("Original input: " + Arrays.toString(nums));
        while(left <= right){
            int val = nums[right];
            nums[right] = nums[left];
            nums[left] = val;
            left++;
            right --;
         }


        System.out.println("Changed input: " + Arrays.toString(nums));
        // Expected output example: [5, 4, 3, 2, 1]
    }
}
