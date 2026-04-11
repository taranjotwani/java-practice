package com.taran.dsa.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem 17: Longest substring without repeating characters.
 * TODO: Implement solution.
 */
public class Problem17 {

    public static void main(String[] args) {
        String text = "abcabczbdebt";

        String longest = findLongestSubstring(text);
        
        System.out.println("Sample input: " + text);
        System.out.println("Longest String : " + longest);
        // Expected output example: 3 ("abc")
    }


    /**
     * Problem 17: Longest substring without repeating characters.
     * LeetCode 3 | Sliding Window + HashMap
     * Time: O(n) | Space: O(min(n, m)) where m = charset size
     */
        public static String findLongestSubstring(String s) {
            String longestString = "";
            Map<Character, Integer> charIndex = new HashMap<>();
            int maxLen = 0;
            int left = 0;

            for (int right = 0; right < s.length(); right++) {
                char c = s.charAt(right);

                // If duplicate found inside current window, jump left past it
                if (charIndex.containsKey(c) && charIndex.get(c) >= left) {
                    left = charIndex.get(c) + 1;
                }

                charIndex.put(c, right);
                maxLen = Math.max(maxLen, right - left + 1);
                if(longestString.length() < maxLen) {
                    System.out.println("longestString: " + longestString + ", maxLen: " + maxLen);
                    longestString = s.substring(left, right + 1);
                }
            }

            return longestString;
        }
}
