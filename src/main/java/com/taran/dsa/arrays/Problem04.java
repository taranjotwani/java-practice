package com.taran.dsa.arrays;

import java.util.Arrays;

/**
 * Problem 04: Move all zeros to the end of the array.
 * TODO: Implement solution.
 */
public class Problem04 {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        nums = moveZerosToTheEnd(nums);
        System.out.println("Sample input: " + Arrays.toString(nums));
    }

    private static int[] moveZerosToTheEnd(int[] nums) {
        int oldIndex = 0;
        int indexPosition = 0;
        int[] newArr = new int[nums.length];
        while(oldIndex < nums.length){
            if(nums[oldIndex] != 0){ 
                newArr[indexPosition] = nums[oldIndex];
                oldIndex ++; indexPosition++;
            }else{
                oldIndex++;
            }
        }
        return newArr;
    }
}
