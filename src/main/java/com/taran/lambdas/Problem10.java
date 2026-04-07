package com.taran.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Problem 10: Given a List<String>, use stream().filter().map().collect() to
 * return a list of strings that start with A, converted to uppercase.
 */
public class Problem10 {


    public static void main(String[] args) {
        Predicate<String> startsWithA = str -> str.startsWith("A");
        Function<String, String> changeToUppercase = s -> s.toUpperCase();
        // Example String values
        String[] examples = new String[]{
            "Anoop", "Barnali", "Ananya", "Amarthya"
        };
        List<String> values = new ArrayList(Arrays.asList(examples));
        values.stream().filter(startsWithA)
                        .map(changeToUppercase)
                        .collect(Collectors.toList())
                        .forEach(System.out::println);
    }
}
