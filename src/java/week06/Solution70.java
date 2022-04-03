package week06;

/**
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * f[n] = f[n - 1] + f[n - 2]
 * 可以开一个f[n+1]的数组  f[0] = 1,f[1] = 1,f[2] = f[0] + [1] ....  f[n] = f[n - 1] + f[n - 2]
 *
 * 优化存储空间  由于 f[i]的值只跟i - 1 和 i - 2 有关 不关心i-2之前的结果
 * 所以   使用三个变量存储  滚动更新
 */
public class Solution70 {
    public int climbStairs(int n) {
        if (n < 3) return n;
        int f1 = 1;
        int f2 = 2;
        int f3 = 3;
        for (int i = 3; i <= n; i++) {
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
        }
        return f3;
    }
}
