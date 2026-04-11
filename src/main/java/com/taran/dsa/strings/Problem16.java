package com.taran.dsa.strings;

/**
 * Problem 16: Count and say - generate the nth term.
 * TODO: Implement solution.
 */
public class Problem16 {

    public static void main(String[] args) {
        int n = 5;
        System.out.println(countAndSay(n));
        System.out.println("Sample input: n=" + n);
        // Expected output example: "111221"
    }

    public static String countAndSay(int n) {
    String current = "1";
    System.out.println("Initial n: " + n);
    System.out.println("Initial current: " + current);

    while (--n > 0) {
        StringBuilder next = new StringBuilder();
        System.out.println("\nAfter decrement, n: " + n);
        System.out.println("Current at loop start: " + current);
        System.out.println("Initial next: " + next);

        for (int i = 0; i < current.length(); ) {
            int end = i;
            System.out.println("\nStart of group -> i: " + i + ", end: " + end + ", char: " + current.charAt(i));

            // Expand the window while characters match
            while (end < current.length() && current.charAt(end) == current.charAt(i)) {
                end++;
                System.out.println("Expanding group -> end: " + end);
            }

            // Append count + digit
            next.append(end - i).append(current.charAt(i));
            System.out.println("Appended count: " + (end - i));
            System.out.println("Appended digit: " + current.charAt(i));
            System.out.println("next after append: " + next);
            i = end;
            System.out.println("Updated i: " + i);
        }

        current = next.toString();
        System.out.println("Current term: " + current);
    }

    return current;
}
}
 