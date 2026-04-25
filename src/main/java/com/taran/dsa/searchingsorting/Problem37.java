package com.taran.dsa.searchingsorting;

/**
 * Problem 37: Find first and last position of element in sorted array.
 * TODO: Implement solution.
 */
public class Problem37 {

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 7;
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + right/2;
            if(nums[mid] == target){
                left = mid;
                right = mid;
                while(left >= 0 && nums[left] == target){
                    left--;
                }
                while(right < nums.length && nums[right] == target){
                    right++;
                }
                break;
            }else if(nums[mid] < target){
                left++;
            }else if(nums[mid] > target){
                right++;
            }
        }
         
        System.out.println("First and last position of element in sorted array: [" + (left + 1) + ", " + (right - 1) + "]"  );

        System.out.println("Sample input: nums=" + java.util.Arrays.toString(nums) + ", target=" + target);
        // Expected output example: [3, 4]
    }
}
