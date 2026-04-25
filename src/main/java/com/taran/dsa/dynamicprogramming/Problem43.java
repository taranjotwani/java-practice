package com.taran.dsa.dynamicprogramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Problem 43: Word Break
 * Given a string s and a dictionary of words, return true if s can be segmented
 * into space-separated words that are all in the dictionary.
 * LeetCode 139 | Dynamic Programming + HashMap/HashSet
 * Time: O(n^2) | Space: O(n)
 * TODO: Implement solution.
 */
public class Problem43 {

    public static void main(String[] args) {
        String s1 = "leetcode";
        String[] dict1 = {"leet", "code"};

        String s2 = "applepenapple";
        String[] dict2 = {"apple", "pen"};

        String s3 = "catsanddogs";
        String[] dict3 = {"cat", "cats", "and", "sand", "dog", "dogs"};

        String s4 = "a";
        String[] dict4 = {"b"};

        System.out.println("Sample input 1: s = \"" + s1 + "\", dictionary = " + Arrays.toString(dict1));
        System.out.println("Can segment: " + canSegment(s1, new HashSet<>(Arrays.asList(dict1))));
        // Expected output: true

        System.out.println("\nSample input 2: s = \"" + s2 + "\", dictionary = " + Arrays.toString(dict2));
        System.out.println("Can segment: " + canSegment(s2, new HashSet<>(Arrays.asList(dict2))));
        // Expected output: true

        System.out.println("\nSample input 3: s = \"" + s3 + "\", dictionary = " + Arrays.toString(dict3));
        System.out.println("Can segment: " + canSegment(s3, new HashSet<>(Arrays.asList(dict3))));
        // Expected output: false

        System.out.println("\nSample input 4: s = \"" + s4 + "\", dictionary = " + Arrays.toString(dict4));
        System.out.println("Can segment: " + canSegment(s4, new HashSet<>(Arrays.asList(dict4))));
        // Expected output: false
    }

    /**
     * Check if string can be segmented using words from dictionary
     * Hint: dp[i] = true if s[0..i-1] can be segmented
     */
    public static boolean canSegment(String s, Set<String> wordDict) {
        // TODO: Implement this method
        // Use DP where dp[i] represents if s[0..i-1] can be segmented
        return false;
    }
}
