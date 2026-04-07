package com.taran.lambdas;

/**
 * Problem 1: Write a lambda that takes two integers and returns their product.
 * Assign it to a BinaryOperator<Integer>.
 */
public class Problem1 {

    public static void main(String[] args) {
        java.util.function.BinaryOperator<Integer> multiply = (a, b) -> a * b;

        int result = multiply.apply(5, 3);
        System.out.println("The product of 5 and 3 is: " + result);
    }
}
