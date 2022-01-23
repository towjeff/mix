package com.algorithm.maximalSquare3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
    public static void main(String[] args) {
        char[][] matrix = new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        int result = new Solution().maximalSquare(matrix);
        System.out.println(result);
    }

    public  int[][] getNearLess(int[] arr) {
        int[][] res = new int[arr.length][2];
// List<Integer> -> 放的是位置，同样值的东西，位置压在一起
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) { // i -> arr[i] 进栈
// 底 -> 顶， 小 -> 大
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> popIs = stack.pop();
// 取位于下面位置的列表中，最晚加入的那个
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer popi : popIs) {
                    res[popi][0] = leftLessIndex;
                    res[popi][1] = i;
                }
            }
// 相等的、比你小的
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(Integer.valueOf(i));
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> popIs = stack.pop();
// 取位于下面位置的列表中，最晚加入的那个
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer popi : popIs) {
                res[popi][0] = leftLessIndex;
                res[popi][1] = arr.length;
            }
        }
        return res;
    }

    public int maximalSquare(char[][] matrix) {
        int result = 0;
// 以i的水平线为水平底座，长度为1，最大的连续1的高度的柱状
        int[][] columnarHeight = new int[matrix.length][matrix[0].length];
        for (int j = 0; j < matrix[0].length; j++) {
            int continuousCount = 0;
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][j] == '0') {
                    continuousCount = 0;
                } else {
                    continuousCount++;
                }
                columnarHeight[i][j] = continuousCount;
            }
        }
        for (int i = 0; i < columnarHeight.length; i++) {
            int[][] nearLess = getNearLess(columnarHeight[i]);
            for (int j = 0; j < columnarHeight[0].length; j++) {
                result = Math.max(result, Math.min(columnarHeight[i][j], nearLess[j][1] - nearLess[j][0] - 1));
            }
        }
        return result * result;
    }
}