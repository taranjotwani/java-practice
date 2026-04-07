package com.taran.lambdas;

import java.util.function.Function;

/**
 * Problem 6: Using Function.andThen(), chain two functions: one that trims a
 * string, and another that converts it to lowercase.
 * 
 * Function Chaining
 */
public class Problem6 {
    
    
    
    public static void main(String[] args) {
        String name = "Taran Singh";
        Function<String, String> trimFunction = String::trim;
        Function<String, String> lowerCaseFunction = (u) -> u.toLowerCase();
        Function<String, String> chainedFunction = trimFunction.andThen(lowerCaseFunction);
        String result = chainedFunction.apply(name);
        System.out.println(result);
    }
}
