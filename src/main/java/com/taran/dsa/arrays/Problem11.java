package com.taran.dsa.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem 11: 3Sum — find all unique triplets that sum to zero.
 * Given an integer array, return all unique triplets that sum to zero.
 * TODO: Implement solution.
 */
public class Problem11 {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = new ArrayList<>();
        
        // TODO: Implement the findTriplets method
        // result = findTriplets(nums);
        
        System.out.println("Sample input: " + Arrays.toString(nums));
        System.out.println("Triplets that sum to zero: " + result);
        // Expected output example: [[-1, -1, 2], [-1, 0, 1]]
    }

    // TODO: Implement this method
    public static List<List<Integer>> findTriplets(int[] nums) {
        int left = 0; 
        int right = nums.length - 1;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> triplet = new ArrayList<>();
        while(left < right) {
            int sum = nums[left] + nums[right];
            if (sum == 0) {
                
                // Add triplet to result
                // Move left and right pointers
            } else if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        // Add your implementation here
        return new ArrayList<>();
    }
}
