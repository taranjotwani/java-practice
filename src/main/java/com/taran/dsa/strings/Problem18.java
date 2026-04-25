package com.taran.dsa.strings;

/**
 * Problem 18: Longest palindromic substring.
 * TODO: Implement solution.
 */
public class Problem18 {

    public static void main(String[] args) {
        String text = "naran";
        int left, right = 0;
        String longestPalindrome = "";
        for(char value: text.toCharArray()) {
                left = text.indexOf(value);
                right = text.lastIndexOf(value);
                if(left != right) {
                    String subString = text.substring(left, right + 1);
                    if(isPalindrome(subString) 
                        && subString.length() > longestPalindrome.length()) {
                        longestPalindrome = subString;
                    }
                }
        }



        System.out.println("Sample input: " + text);
        // Expected output example: "bab" or "aba"
    }


    public static boolean isPalindrome(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString().equals(str);
    }
}
