package week06;

import java.util.Arrays;
import java.util.List;

/**
 * 120. 三角形最小路径和
 *
 * 造一个 dp 数组，dp[i][j] 表示从 triangle[0][0] 走到 triangle[i][j] 的最小路径和。
 * 起点 dp[0][0] = triangle[0][0]
 * 答案就是 dp[n-1][..] 中的最大值
 *第 i 行的第 j 个元素从哪里来？可以从第 i - 1 行第 j 或第 j - 1 个元素下落过来  得到状态转移方程
 * dp[i][j] = min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle[i][j]
 */
public class Solution120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // 定义：走到第 i 行第 j 个元素的最小路径和是 dp[i][j]
        int[][] dp = new int[n][n];
        for (int i = 0; i < dp.length; i++) {
            // 因为求最小值，所以全都初始化为极大值
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        // base case
        dp[0][0] = triangle.get(0).get(0);
        // 进行状态转移
        for (int i = 1; i < dp.length; i++) {
            List<Integer> row = triangle.get(i);
            for (int j = 0; j < row.size(); j++) {
                // 状态转移方程
                if (j - 1 >= 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + row.get(j);
                } else {
                    dp[i][j] = dp[i - 1][j] + row.get(j);
                }
            }
        }
        // 找出落到最后一层的最小路径和
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < dp[n - 1].length; j++) {
            ans = Math.min(ans, dp[n - 1][j]);
        }
        return ans;
    }
}
