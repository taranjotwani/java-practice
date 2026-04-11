package com.taran.dsa.strings;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Problem 12: Reverse words in a sentence.
 * TODO: Implement solution.
 */
public class Problem12 {

    public static void main(String[] args) {
        String sentence = "Java is fun";
        String[] words = sentence.split(" ");
        List<String> wordList = Arrays.asList(words);
        Collections.reverse(wordList);
        String result = wordList.stream().collect(Collectors.joining(" "));
        System.out.println("Sample input: " + sentence);
        System.out.println("Result is : " + result);
        // Expected output example: "fun is Java"
    }
}
