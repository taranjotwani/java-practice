package com.taran.lambdas;

import java.util.function.Consumer;

/**
 * Problem 9: Using Consumer.andThen(), chain two consumers: one that prints a
 * number, and one that prints its square.
 */
public class Problem9 {
    

    public static void main(String[] args) {
        int testval = 5;
        Consumer<Integer> squareConsumer = val -> System.out.println("Square " + val * val);
        Consumer<Integer> printConsumer = p -> System.out.println("Value " + p);
        Consumer<Integer> chainConsumer = printConsumer.andThen(squareConsumer);
        chainConsumer.accept(testval);
    }
}
