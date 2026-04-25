package com.taran.dsa.linkedlists;

/**
 * Problem 25: Add Two Numbers as LinkedLists
 * Given two numbers represented as linked lists in reverse order,
 * return their sum as a linked list in reverse order.
 * LeetCode 2 | LinkedList + Math
 * Time: O(max(m, n)) | Space: O(1) excluding result
 * TODO: Implement solution.
 */
public class Problem25 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // Sample 1: [2, 4, 3] (reverse of 342) + [5, 6, 4] (reverse of 465) = [7, 0, 8] (reverse of 807)
        ListNode l1 = createList(new int[]{2, 4, 3});
        ListNode l2 = createList(new int[]{5, 6, 4});

        // Sample 2: [0] + [0] = [0]
        ListNode l3 = createList(new int[]{0});
        ListNode l4 = createList(new int[]{0});

        // Sample 3: [9, 9, 9, 9, 9, 9, 9] (reverse of 9999999) + [9, 9, 9, 9] (reverse of 9999) = [8, 9, 9, 9, 0, 0, 0, 1]
        ListNode l5 = createList(new int[]{9, 9, 9, 9, 9, 9, 9});
        ListNode l6 = createList(new int[]{9, 9, 9, 9});

        System.out.println("Sample 1:");
        System.out.println("List 1: " + listToString(l1));
        System.out.println("List 2: " + listToString(l2));
        ListNode result1 = addTwoNumbers(l1, l2);
        System.out.println("Sum: " + listToString(result1));
        // Expected output: 7 -> 0 -> 8

        System.out.println("\nSample 2:");
        ListNode result2 = addTwoNumbers(l3, l4);
        System.out.println("Sum: " + listToString(result2));
        // Expected output: 0

        System.out.println("\nSample 3:");
        System.out.println("List 1: " + listToString(l5));
        System.out.println("List 2: " + listToString(l6));
        ListNode result3 = addTwoNumbers(l5, l6);
        System.out.println("Sum: " + listToString(result3));
        // Expected output: 8 -> 9 -> 9 -> 9 -> 0 -> 0 -> 0 -> 1
    }

    /**
     * Add two numbers represented as linked lists
     * Hint: Iterate through both lists, add corresponding digits with carry
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // TODO: Implement this method
        // Create a dummy node to simplify the logic
        // Handle carry values as you iterate
        return null;
    }

    // Helper method to create a linked list from an array
    private static ListNode createList(int[] arr) {
        if (arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    // Helper method to convert linked list to string
    private static String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode current = head;
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) sb.append(" -> ");
            current = current.next;
        }
        return sb.toString();
    }
}
