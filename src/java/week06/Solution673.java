package week06;

/**
 * 673. 最长递增子序列的个数
 * 给定一个未排序的整数数组 nums ， 返回最长递增子序列的个数 。
 * 注意 这个数列必须是 严格 递增的。
 * 定义 f[i] 为考虑以 nums[i] 为结尾的最长上升子序列的长度
 * 最终答案为所有 f[0...(n - 1)] 中的最大值。
 * 定义 g[i] 为考虑以 nums[i] 结尾的最长上升子序列的个数。
 *
 *
 *
 */
public class Solution673 {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] f = new int[n], g = new int[n];
        int max = 1;
        for (int i = 0; i < n; i++) {
            f[i] = g[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {  // nums[i] 可以接在 nums[j] 后面形成上升子序列
                    if (f[i] < f[j] + 1) {
                        f[i] = f[j] + 1;
                        g[i] = g[j];
                    } else if (f[i] == f[j] + 1) { //找到了一个新的符合条件的前驱，此时将值继续累加到方案数当中，即有 g[i] += g[j]
                        g[i] += g[j];
                    }
                }
            }
            max = Math.max(max, f[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (f[i] == max) ans += g[i];
        }
        return ans;
    }
}
