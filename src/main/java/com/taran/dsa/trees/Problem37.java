package com.taran.dsa.trees;

import java.util.*;

/**
 * Problem 37: Binary Tree Zigzag Level Order Traversal
 * Given a binary tree, return its level order traversal as a list of lists,
 * but alternate between left-to-right and right-to-left at each level.
 * LeetCode 103 | BFS + Deque
 * Time: O(n) | Space: O(n)
 * TODO: Implement solution.
 */
public class Problem37 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // Sample tree:        3
        //                    / \
        //                   9  20
        //                     /  \
        //                    15   7
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        // Sample tree 2:  1
        TreeNode root2 = new TreeNode(1);

        System.out.println("Sample tree 1 (level-order: [3, 9, 20, null, null, 15, 7])");
        System.out.println("Zigzag traversal: " + zigzagLevelOrder(root1));
        // Expected output: [[3], [20, 9], [15, 7]]

        System.out.println("\nSample tree 2: [1]");
        System.out.println("Zigzag traversal: " + zigzagLevelOrder(root2));
        // Expected output: [[1]]
    }

    /**
     * Zigzag level order traversal
     * Hint: Use BFS with a deque. Add left to right on even levels, right to left on odd levels.
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // TODO: Implement this method
        // Use queue (or deque) for BFS
        // Alternate direction when adding to current level list
        return new ArrayList<>();
    }
}
