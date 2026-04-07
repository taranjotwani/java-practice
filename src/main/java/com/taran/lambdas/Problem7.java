package com.taran.lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Problem 7: Using Predicate.and(), combine two predicates: one that checks if
 * a number is positive, and another that checks if it is even. Test it on a
 * list of integers.
 */
public class Problem7 {


    public static void main(String[] args) {
        Integer[] arr = {1, -2, 3, 4, -5};
        List<Integer> values = Arrays.asList(arr);

        Predicate<Integer> positivePredicate = n -> n > 0;
        Predicate<Integer> evenPredicate = n -> n%2 == 0;
        values.stream()
                .filter(positivePredicate.and(evenPredicate))
                .forEach(System.out::println);

     }
}
