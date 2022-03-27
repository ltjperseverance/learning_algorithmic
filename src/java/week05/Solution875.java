package week05;

/**
 * 875. 爱吃香蕉的珂珂
 */
public class Solution875 {
    public int minEatingSpeed(int[] piles, int H) {
        int left = 1;
        int right = 1000000000 + 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (eatDoneOnHours(piles, mid) <= H) {  // 在符合条件的速度中选择最小的
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     *
     * @param piles
     * @param x 吃香蕉的速度
     * @return 吃完耗时
     */
    int eatDoneOnHours(int[] piles, int x) {
        int hours = 0;
        for (int i = 0; i < piles.length; i++) {
            hours += piles[i] / x;
            if (piles[i] % x > 0) {
                hours++;
            }
        }
        return hours;
    }
}
