package com.taran.dsa.searchingsorting;

/**
 * Problem 38: Search in a rotated sorted array.
 * TODO: Implement solution.
 * 
 * 
 * // One of a kind solution. Need to rememeber this approach. 
 */
public class Problem38 {

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 2;
        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            int mid = left + (right - left)/2;
            // Check if the mid is the target.
            if(nums[mid] == target){
                System.out.println("Value Placement Found : " + mid);
            }
            // Check if the left array is sorted or not
            if(nums[mid] > nums[left]){
                // Check if the target is in the left array or not
                if(nums[left] <= target && target < nums[mid]){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else{
                // Check if the target is in the right array or not
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }
    }
}
