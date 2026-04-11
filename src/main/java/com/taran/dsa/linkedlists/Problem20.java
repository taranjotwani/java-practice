package com.taran.dsa.linkedlists;

/**
 * Problem 20: Detect a cycle in a linked list (Floyd's algorithm).
 * TODO: Implement solution.
 */
public class Problem20 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        // Sample linked list with a cycle
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        head.next = second;
        second.next = third;
        third.next = second; // Creates a cycle

        boolean hasCycle = detectCycle(head);
        System.out.println("Does the linked list have a cycle? " + hasCycle); // Expected output: true
    }

    public static boolean detectCycle(ListNode head) {
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;           // Move slow by 1
            fast = fast.next.next;      // Move fast by 2

            if (slow == fast) {         // A cycle is detected
                return true;
            }
        }

        return false; // No cycle found
    }    
}
