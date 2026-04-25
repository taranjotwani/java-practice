package com.taran.dsa.trees;

import java.util.*;

/**
 * Problem 36: Kth Smallest Element in a BST
 * Given a binary search tree, find the kth smallest element.
 * LeetCode 230 | Inorder Traversal
 * Time: O(n) worst case, O(h + k) average | Space: O(h)
 * TODO: Implement solution.
 */
public class Problem36 {

    // Binary tree node class
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // Sample tree 1:      3
        //                     / \
        //                    1   4
        //                     \
        //                      2
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.right = new TreeNode(2);

        // Sample tree 2:         5
        //                       / \
        //                      3   6
        //                     / \
        //                    2   4
        //                   /
        //                  1
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.left.left.left = new TreeNode(1);

        System.out.println("Sample tree 1 (level-order: [3, 1, 4, null, 2])");
        System.out.println("1st smallest: " + kthSmallest(root1, 1));
        // Expected output: 1
        System.out.println("2nd smallest: " + kthSmallest(root1, 2));
        // Expected output: 2
        System.out.println("3rd smallest: " + kthSmallest(root1, 3));
        // Expected output: 3

        System.out.println("\nSample tree 2 (level-order: [5, 3, 6, 2, 4, null, null, 1])");
        System.out.println("3rd smallest: " + kthSmallest(root2, 3));
        // Expected output: 3
    }

    /**
     * Find kth smallest element in BST
     * Hint: Inorder traversal gives elements in sorted order. Stop at kth element.
     */
    public static int kthSmallest(TreeNode root, int k) {
        // TODO: Implement this method
        // Use inorder traversal (left, root, right) to get sorted elements
        return -1;
    }
}
