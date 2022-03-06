package week02;

/**
 * 第二周作业四
 * 560. 和为 K 的子数组
 */
public class Solution560 {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // 构造前缀和
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 0; i < n; i++)
            preSum[i + 1] = preSum[i] + nums[i];

        int res = 0;
        // 穷举所有子数组
        for (int i = 1; i <= n; i++)
            for (int j = 0; j < i; j++)
                // 子数组 nums[j..i-1] 的元素和
                if (preSum[i] - preSum[j] == k)
                    res++;

        return res;
    }
}
