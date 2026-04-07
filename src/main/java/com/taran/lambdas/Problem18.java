package com.taran.lambdas;

import java.util.function.Function;

/**
 * Problem 18: Write a method that accepts a Function<Integer, Integer> and
 * applies it n times to an initial value. Test it by doubling a number
 * 4 times.
 */
public class Problem18 {

    static final Function<Integer, Integer> doubleFunction = num -> num * 2;


    public static int sendFunction(Function<Integer, Integer> function, int num, int limit){
        for(int i = 0; i < limit; i++){
            num = function.apply(num);
        }
        return num;
    }

    public static void main(String[] args) {
        int result = Problem18.sendFunction(doubleFunction, 2, 4);
        System.out.println("Result after doubling 4 times: " + result);
    }
}
