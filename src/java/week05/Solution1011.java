package week05;

import java.util.Arrays;

/**
 * 1011. 在 D 天内送达包裹的能力
 * 传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。
 *
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 *
 * 返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 二分答案
 * 检查按W重量分桶 day天分完行不行
 * 在符合条件的答案中找最小的值
 *
 */
public class Solution1011 {
    public int shipWithinDays(int[] weights, int days) {
        //二分查找 r = 数组的总和， l = 数组的最大值
        int right = Arrays.stream(weights).sum();
        int left = Arrays.stream(weights).max().getAsInt();

        while (left < right) {
            int mid = (left + right) / 2;
            if (validateOnWeighht(weights, days, mid)){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return right;
    }
    //判断最低运载能力为W的时候能否在days天内送达
    boolean validateOnWeighht(int[] weights, int days, int W){
        //天数计数，初始化为1
        int count = 1;
        //每天的包裹总量
        int singleWeight = 0;
        for (int weight : weights) {
            //累计包裹总量
            singleWeight += weight;
            //如果累计包裹总量singleWeight > H，天数+1
            if(singleWeight > W){
                ++count;
                singleWeight = weight;
            }
            //如果当前累计的天数count > D，说明当前H不满足条件，返回false
            if(count > days){
                return false;
            }
        }
        //说明当前H满足条件，返回true
        return true;
    }
}
