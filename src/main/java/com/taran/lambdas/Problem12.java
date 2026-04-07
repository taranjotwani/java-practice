package com.taran.lambdas;

import java.util.Arrays;
import java.util.List;

/**
 * Problem 12: Given a List<String>, sort the list by string length ascending
 * using a lambda comparator.
 */
public class Problem12 {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("elephant", "cat", "programming", "java", "a", "stream");
        words.sort((val1, val2) -> Integer.compare(val1.length(), val2.length()));
        words.stream().forEach(System.out::println);        
    }
}
