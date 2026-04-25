package com.taran.dsa.searchingsorting;

import java.util.Arrays;

/**
 * Problem 36: Binary search on sorted array.
 * TODO: Implement solution.
 */
public class Problem36 {

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9, 123};
        int target = 11;
        int left = 0;
        int right = nums.length - 1;
        int result = -1;
        Arrays.sort(nums); // this is gonna set the time complexeity
        while(left <= right) {
            int mid = left + right /2;
            if(nums[mid] == target){
                result = mid;
                break;
            }else if(nums[mid] <= target){
                left++;
            }else if(nums[mid] >= target){
                right++;
            }
        }
        if(result != -1){
            System.out.println("Element found at index: " + result);
        }else{
            System.out.println("Element not found in the array.");
        }

        System.out.println("Sample input: nums=" + java.util.Arrays.toString(nums) + ", target=" + target);

    }
}
