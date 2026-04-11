package com.taran.dsa.strings;

/**
 * Problem 11: Check if a string is a palindrome.
 * TODO: Implement solution.
 */
public class Problem11 {

    public static void main(String[] args) {
        String text = "racecar";
        StringBuilder sb = new StringBuilder(text);
        System.out.println("Is it palindrome? "  + sb.reverse().toString().equals(text));
    }
}
