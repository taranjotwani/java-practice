package com.taran.dsa.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem 19: Group Anagrams
 * Given an array of strings, group the anagrams together.
 * LeetCode 49 | HashMap + Sorting
 * Time: O(n*k log k) where k is max length of a string | Space: O(n*k)
 * TODO: Implement solution.
 */
public class Problem19 {

    public static void main(String[] args) {
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        String[] strs2 = {""};
        String[] strs3 = {"a"};
        String[] strs4 = {"ab", "ba", "abc", "bca", "cab", "aabbcc"};

        System.out.println("Sample input 1: " + java.util.Arrays.toString(strs1));
        System.out.println("Grouped anagrams: " + groupAnagrams(strs1));
        // Expected output: [[bat], [nat, tan], [ate, eat, tea]]

        System.out.println("\nSample input 2: " + java.util.Arrays.toString(strs2));
        System.out.println("Grouped anagrams: " + groupAnagrams(strs2));
        // Expected output: [[""]]

        System.out.println("\nSample input 3: " + java.util.Arrays.toString(strs3));
        System.out.println("Grouped anagrams: " + groupAnagrams(strs3));
        // Expected output: [[a]]

        System.out.println("\nSample input 4: " + java.util.Arrays.toString(strs4));
        System.out.println("Grouped anagrams: " + groupAnagrams(strs4));
        // Expected output: [[ab, ba], [abc, bca, cab], [aabbcc]]
    }

    /**
     * Group anagrams together
     * Hint: Sort each string and use as key in HashMap
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        // TODO: Implement this method
        // Hint: Use HashMap where key is sorted string, value is list of anagrams
        return new ArrayList<>();
    }
}
