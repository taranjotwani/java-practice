package com.taran.lambdas;

import java.util.function.Predicate;

/**
 * Problem 2: Write a lambda that checks if a string is a palindrome.
 * Assign it to a Predicate<String>.
 */
public class Problem2 {
    public static void main(String[] args) {
        String a = "arc";
        Predicate<String> predicate = s -> {
            String reversed = new StringBuilder(s).reverse().toString();
            return s.equals(reversed);
        };
        System.out.println(predicate.test(a)); 
    }
}
