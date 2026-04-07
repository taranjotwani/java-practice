package com.taran.lambdas;

/**
 * Problem 15: Define a custom functional interface TriFunction<A, B, C, R>
 * that takes three inputs and returns a result. Implement it with a lambda
 * that adds three integers.
 */
public class Problem15 {

    @FunctionalInterface
    private interface TriFunction<A, B, C, R>{
        R test(A a, B b, C c);
    }


    public static void main(String[] args) {
        TriFunction<Integer, Integer, Integer, Integer> customFunction = (a, b, c) -> a + b + c;
    
        int result = customFunction.test(3, 4, 5);
        System.out.println("Result : " + result);
    }
}
