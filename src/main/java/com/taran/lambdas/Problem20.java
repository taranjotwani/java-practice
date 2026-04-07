package com.taran.lambdas;

import java.util.function.Function;

/**
 * Problem 20: Implement a simple pipeline using Function.andThen() that parses
 * a string to integer, multiplies by 3, converts back to string, and appends
 * units. Chain all steps as lambdas.
 */
public class Problem20 {

    public static void main(String[] args) {
    Function<String, Integer> convertToInteger = Integer::parseInt;
    Function<String, String> pipeline = convertToInteger
            .andThen(num -> num * 3)
            .andThen(String::valueOf)
            .andThen(str -> str + " units");

    System.out.println(pipeline.apply("5")); // 15 units            
    }
}
