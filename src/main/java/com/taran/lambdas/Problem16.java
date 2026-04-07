package com.taran.lambdas;

/**
 * Problem 16: Define a functional interface StringTransformer with method
 * String transform(String input). Write two lambda implementations: one that
 * reverses the string, and one that removes all vowels.
 */
public class Problem16 {


    /*
     * Create the functional interface for the action description. 
     */
    @FunctionalInterface
    private interface StringTransformer<I, R>{
        R action(I i);
    }

    public static void main(String[] args) {
        String value = "Taran";

        StringTransformer<String, String> reverseFunction = str -> {
            StringBuilder builder = new StringBuilder(str);
            return builder.reverse().toString();
        };
        StringTransformer<String, String> remVowelsFunctions = str -> {
            return str.replaceAll("[AEIOUaeiou]", "");
        };

        System.out.println("Reverse : " + reverseFunction.action(value));
        System.out.println("Devowel : " + remVowelsFunctions.action(value));
    }

}
