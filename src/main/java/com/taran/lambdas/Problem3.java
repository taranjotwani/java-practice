package com.taran.lambdas;

import java.util.function.Function;

/**
 * Problem 3: Write a lambda that converts a string to uppercase.
 * Assign it to a Function<String, String>.
 */
public class Problem3 {


    public static void main(String[] args) {
        String value = "future";
        Function<String, String> uppercaseFunction = String::toUpperCase;
        System.out.println(uppercaseFunction.apply(value));        
    }
}
