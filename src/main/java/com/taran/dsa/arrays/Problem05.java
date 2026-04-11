package com.taran.dsa.arrays;

/**
 * Problem 05: Find a pair with a given sum (Two Sum).
 * TODO: Implement solution.
 */
public class Problem05 {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 26;
        int leftIndex = 0;
        int rightIndex = 0;
        System.out.println("Sample input: nums=" + java.util.Arrays.toString(nums) + ", target=" + target);
        while(leftIndex <= nums.length){
            if(nums[leftIndex] + nums[rightIndex] - target == 0){
                System.out.println("Pair found : " + nums[leftIndex] + " " + nums[rightIndex]);
                break;
            }else{
                rightIndex ++;
            }
            if(rightIndex == nums.length){
                leftIndex ++;
                rightIndex = 0; 
            }
            if(leftIndex == nums.length){
                System.out.println("No pair found");
                break;
            }
        }
    }
}
