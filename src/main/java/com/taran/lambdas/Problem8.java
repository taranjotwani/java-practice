package com.taran.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Problem 8: Using Predicate.negate(), filter out all strings longer than 5
 * characters from a list.
 */
public class Problem8 {

    public static void main(String args[]){
        String[] values = new String[]{"abc", "taran", "barnali", "naila"};
        List<String> valueList = new ArrayList(Arrays.asList(values));
        Predicate<String> lengthPredicate = n -> n.length() < 5;
        valueList.stream().filter(lengthPredicate.negate()).forEach(System.out::println);
    }
}
