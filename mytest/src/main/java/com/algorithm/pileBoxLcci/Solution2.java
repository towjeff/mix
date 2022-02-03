package com.algorithm.pileBoxLcci;

import java.util.Arrays;

class Solution2 {
    public static void main(String[] args) {
        int[][] box = new int[][]{{19, 2, 3}, {3, 20, 19}, {12, 16, 18}, {5, 11, 3}, {17, 11, 15}, {17, 1, 18}, {7, 14, 10}, {19, 19, 15}, {9, 15, 7}, {1, 9, 1}, {10, 3, 3}, {20, 11, 18}, {15, 13, 12}, {16, 10, 11}, {16, 7, 1}, {9, 9, 10}, {2, 2, 12}, {6, 19, 17}, {16, 20, 10}, {20, 20, 8}, {6, 12, 5}, {20, 3, 8}, {2, 4, 1}, {7, 14, 5}, {11, 1, 2}, {13, 2, 4}, {10, 9, 9}, {8, 12, 16}, {13, 20, 5}, {1, 3, 9}, {5, 17, 18}, {5, 15, 12}, {9, 4, 14}, {14, 16, 8}, {13, 17, 15}, {17, 6, 19}, {15, 14, 1}, {15, 9, 13}, {18, 19, 20}, {14, 11, 16}, {16, 6, 4}, {18, 11, 12}, {3, 6, 10}, {12, 2, 11}, {16, 10, 7}, {6, 10, 7}, {18, 16, 4}, {13, 5, 11}, {13, 12, 10}, {4, 12, 15}, {4, 2, 10}, {9, 1, 4}};
        System.out.println(new Solution2().pileBox(box));
    }

    public int pileBox(int[][] box) {
        Arrays.sort(box, (box1, box2) -> box1[1] - box2[1]);
        // 以 box[i] 为最下面的箱子的最大高度
        int[] maxHi = new int[box.length];
        int maxResult = Integer.MIN_VALUE;
        for (int i = 0; i < box.length; i++) {
            maxHi[i] = box[i][2];
            int twi = -1, tdi = -1, thi = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (box[j][0] < box[i][0] && box[j][1] < box[i][1] && box[j][2] < box[i][2]
                        && (box[j][0] >= twi || box[j][1] >= tdi || box[j][2] >= thi)) {
                    twi = box[j][0];
                    tdi = box[j][1];
                    thi = box[j][2];
                    maxHi[i] = Math.max(maxHi[i], maxHi[j] + box[i][2]);
                }
            }
            maxResult = Math.max(maxResult, maxHi[i]);
        }
        return maxResult;
    }
}
