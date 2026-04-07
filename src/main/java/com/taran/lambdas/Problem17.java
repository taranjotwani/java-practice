package com.taran.lambdas;

/**
 * Problem 17: Define a functional interface Validator<T> with method boolean
 * validate(T value). Use it to validate that a password string is at least
 * 8 characters and contains a digit.
 */
public class Problem17 {

        @FunctionalInterface
        private interface Validator<A>{
            boolean validate(A a);
        }


        public static void main(String[] args) {
            String pasword = "Honda@K720m";
            Validator<String> passwordValidator = str -> str.length() > 8 && str.matches(".*\\d.*");
            System.out.println("Is Password Valid : " + passwordValidator.validate(pasword));
        }

}
