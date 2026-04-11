package com.taran.dsa.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/***
 * Find the K largest elements in an array.
 */


public class Problem10Extension {

    public static void main(String[] args) {
        Integer[] nums = {3, 2, 1, 5, 6, 45};
        int k = 1;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        priorityQueue.addAll(Arrays.asList(nums));
        for(int i = 0; i <= k-1; i++){
            System.out.println(priorityQueue.peek());
        }
    }
    
}
