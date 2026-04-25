package com.taran.dsa.arrays;

public class DishwasherBalancer {
    public static int minMoves(int[] arr) {
        int n = arr.length;
        int total = 0;
        for (int num : arr) total += num;
        if (total % n != 0) return -1;
        int target = total / n;
        int moves = 0;
        int prefix = 0;
        for (int num : arr) {
            prefix += num - target;
            moves = Math.max(moves, Math.abs(prefix));
        }
        return moves;
    }

    public static void main(String[] args) {
        int[] loads = {0, 2, 0};
        System.out.println(minMoves(loads));  // Output: 3
    }
}