package week02;

import java.util.HashMap;

/**
 * 第二周作业三
 * 1074. 元素和为目标值的子矩阵数量
 */
public class Solution1074 {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ans = 0;
        // 前缀和预处理
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] += matrix[i][j-1];
            }
        }
        for (int k = 0; k < n; k++) {
            for (int j = k; j < n; j++) {
                int sum = 0;
                // 加入{0,1} 以0,0为左上角的区间和等于target，属于边界情况
                HashMap<Integer,Integer> map = new HashMap<>(){{
                    put(0,1);
                }};
                for (int i = 0; i < m; i++) {
                    //计算当前区间和
                    sum += (k==0? matrix[i][j] : matrix[i][j] - matrix[i][k-1]);
                    if(map.containsKey(sum-target)) ans += map.get(sum-target);
                    map.put(sum,map.getOrDefault(sum, 0) + 1);
                }

            }
        }
        return ans;
    }
}
