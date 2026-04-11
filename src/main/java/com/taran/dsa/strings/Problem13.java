package com.taran.dsa.strings;

import java.util.Arrays;

/**
 * Problem 13: Check if two strings are anagrams.
 * TODO: Implement solution.
 */
public class Problem13 {

    public static void main(String[] args) {
        String a = "listen";
        String b = "silent";

        for(char value : b.toCharArray()){
           a = a.replace(value, Character.MIN_VALUE);
        }

        System.out.println("a after removing characters of b: " + a);

        if(a.trim().length() == 0){
            System.out.println("Is it anagram? true");
        } else {
            System.out.println("Is it anagram? false");
        }



        System.out.println("Sample input: a=" + a + ", b=" + b);
        // Expected output example: true
    }
}
