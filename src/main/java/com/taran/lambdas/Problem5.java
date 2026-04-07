package com.taran.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Problem 5: Write a Supplier<List<String>> that returns a new empty ArrayList
 * each time it is called.
 */
public class Problem5 {

    public static void main(String[] args){
        Supplier<List<String>> listSupplier = () -> new ArrayList<String>();
        List<String> list = listSupplier.get(); 
    }
}
