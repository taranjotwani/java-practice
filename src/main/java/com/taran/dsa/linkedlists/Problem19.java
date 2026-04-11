package com.taran.dsa.linkedlists;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Problem 19: Reverse a singly linked list.
 * TODO: Implement solution.
 */
public class Problem19 {



public static void main(String[] args) {
    Integer[] n1 = {8,9,0,9,9,9,9};
    LinkedList<Integer> list1 = new LinkedList<>(Arrays.asList(n1));
    Collections.reverse(list1);
    System.out.println("Sample linked list: " + list1);

}



public static void myAlgo(String[] args) {
    Integer[] n1 = {9,9,9,9,9,9,9};
    Integer[] n2 = {9,9,9,9};
    LinkedList<Integer> list1 = new LinkedList<>(Arrays.asList(n1));
    LinkedList<Integer> list2 = new LinkedList<>(Arrays.asList(n2));
    LinkedList<Integer> linkedList = new LinkedList<>();
    Collections.reverse(list1);
    Collections.reverse(list2);
    var value1 = list1.stream().map(String::valueOf).collect(Collectors.joining(""));
    var value2 = list2.stream().map(String::valueOf).collect(Collectors.joining(""));
    Integer result = Integer.valueOf(value1) + Integer.valueOf(value2);
    StringBuilder resultBuilder = new StringBuilder(String.valueOf(result));
    resultBuilder.reverse();
    result.toString().chars().forEach(
            c -> linkedList.add(Character.getNumericValue(c))
    );
    System.out.println("Sample linked list: " + linkedList);
    // Expected output example: [5, 4, 3, 2, 1]
}


public static LinkedList<Integer> addReversedLists(LinkedList<Integer> l1, LinkedList<Integer> l2) {
    LinkedList<Integer> result = new LinkedList<>();
    Iterator<Integer> it1 = l1.iterator();
    Iterator<Integer> it2 = l2.iterator();
    int carry = 0;

    while (it1.hasNext() || it2.hasNext() || carry != 0) {
        int sum = carry;
        if (it1.hasNext()) sum += it1.next();
        if (it2.hasNext()) sum += it2.next();
        result.add(sum % 10);   // digit in reverse order
        carry = sum / 10;
    }
    return result; // already in reverse order
}
}
