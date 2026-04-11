package com.taran.dsa.strings;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Problem 15: Find the longest word in a sentence.
 * TODO: Implement solution.
 */
public class Problem15 {

    public static void main(String[] args) {
    String pattern = "Java Backend Developer Practice" ;   
    String longestWord = Arrays.stream(pattern.split(" "))
        .max(Comparator.comparingInt(String::length)) .orElse("");;
    System.out.println("Longest word is: " + longestWord);
    }
}
