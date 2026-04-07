package com.taran.lambdas;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * Problem 11: Given a List<Integer>, use streams to find the sum of all even
 * numbers using reduce().
 * 
 * Note: When you call Arrays.stream() on a primitive array, it returns a specialized 
 * stream (IntStream, LongStream, etc.) rather than a generic Stream<Integer>.
 */
public class Problem11 {

    public static void main(String[] args) {

        Predicate<Integer> evenPredicate = n -> n % 2 == 0;
        Integer[] values = new Integer[]{1,2,3,4,5};
        int sum = Arrays.stream(values)
                    .filter(evenPredicate)
                    .mapToInt(Integer::intValue)
                    .sum();
        System.out.println("Sum : " + sum);
    }
}
