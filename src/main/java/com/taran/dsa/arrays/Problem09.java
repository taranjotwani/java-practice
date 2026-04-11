package com.taran.dsa.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Problem 09: Find duplicates in an array.
 * TODO: Implement solution.
 */
public class Problem09 {

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> duplicates = new ArrayList<>();
        Set<Integer> numberSet = new HashSet<>();
        Arrays.stream(nums).forEach(value -> {
            if(!numberSet.add(value)){
                duplicates.add(value);        
            }
        });
        System.out.println("Sample input: " + java.util.Arrays.toString(nums)
                + "\nDuplicate values in the array: " + duplicates);
        // Expected output example: [2, 3]
    }
}
