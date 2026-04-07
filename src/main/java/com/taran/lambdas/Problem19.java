package com.taran.lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Problem 19: Given a List<List<Integer>>, use flatMap() to produce a single
 * flattened list of all integers, then filter values greater than 10.
 */
public class Problem19 {



	public static void main(String[] args) {
		List<List<Integer>> sampleNumbers = Arrays.asList(
				Arrays.asList(1, 4, 11, 15),
				Arrays.asList(3, 10, 12),
				Arrays.asList(7, 18, 20, 5)
		);

		List<Integer> filteredNumbers = flattenAndFilter(sampleNumbers);

		System.out.println("Sample data: " + sampleNumbers);
		System.out.println("Flattened values greater than 10: " + filteredNumbers);
	}

    private static List<Integer> flattenAndFilter(List<List<Integer>> sampleNumbers) {
           return sampleNumbers.stream().flatMap(List::stream)
                                  .filter(num -> num < 10)
                                  .collect(Collectors.toList());
    }

}
