package com.taran.dsa.stackqueue;

import java.util.Stack;
import java.util.Arrays;

/**
 * Problem 30: Evaluate Reverse Polish Notation (RPN)
 * Given tokens representing an RPN expression, evaluate it.
 * Operators are +, -, *, /. Division result is truncated towards zero.
 * LeetCode 150 | Stack
 * Time: O(n) | Space: O(n)
 * TODO: Implement solution.
 */
public class Problem30 {

    public static void main(String[] args) {
        String[] tokens1 = {"2", "1", "+", "3", "*"};
        String[] tokens2 = {"4", "13", "5", "/", "+"};
        String[] tokens3 = {"15", "7", "1", "1", "-", "-", "/", "3", "*", "2", "1", "1", "+", "-", "-"};
        String[] tokens4 = {"3", "11", "+"};

        System.out.println("Sample input 1: " + Arrays.toString(tokens1));
        System.out.println("Result: " + evalRPN(tokens1));
        // Expected output: 9 ((2 + 1) * 3 = 9)

        System.out.println("\nSample input 2: " + Arrays.toString(tokens2));
        System.out.println("Result: " + evalRPN(tokens2));
        // Expected output: 6 (4 + (13 / 5) = 4 + 2 = 6)

        System.out.println("\nSample input 3: " + Arrays.toString(tokens3));
        System.out.println("Result: " + evalRPN(tokens3));
        // Expected output: 5

        System.out.println("\nSample input 4: " + Arrays.toString(tokens4));
        System.out.println("Result: " + evalRPN(tokens4));
        // Expected output: 14
    }

    /**
     * Evaluate Reverse Polish Notation
     * Hint: Use a stack. When you see a number, push it. When you see an operator, pop two numbers, apply operator, push result.
     */
    public static int evalRPN(String[] tokens) {
        // TODO: Implement this method
        return 0;
    }
}
