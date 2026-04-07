package com.taran.lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Problem 14: Given a List<Integer>, use Collectors.groupingBy() to group
 * numbers into two buckets: even and odd.
 * 
 * Result = {0=[2, 4, 6, 8, 10], 1=[1, 3, 5, 7, 9]}
 */
public class Problem14 {
    public static void main(String[] args) {
    Integer[] values = new Integer[]{1,2,3,4,5,6,7,8,9,10};
    Function<Integer, Integer> evenPredicate = w -> w%2;
     Map<Integer,List<Integer>> result =  Arrays.stream(values)
                                                .collect(Collectors.groupingBy(evenPredicate));
     System.out.println(result);
    }
}
