package com.taran.lambdas;

import java.util.function.Consumer;

/**
 * Problem 4: Write a lambda that prints Hello, <name> given a name string.
 * Assign it to a Consumer<String>.
 */
public class Problem4 {

    public static void main(String[] args) {
        String name = "Taran";
        Consumer<String> helloConsumer = n -> System.out.println("Hello, " + n);
        helloConsumer.accept(name);
    }
}
