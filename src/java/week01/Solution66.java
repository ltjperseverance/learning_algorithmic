package week01;


/**
 * 第一周作业一
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution66 {
    public int[] plusOne(int[] digits) {
        int pow = 0;
        int[] result = new int[digits.length];
        digits[digits.length-1] = digits[digits.length-1] + 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int curNum = digits[i];
            int newNum = (pow + curNum) % 10;
            result[i] = newNum;
            pow = (pow + curNum) / 10;

        }
        if (pow != 0){
            int[] ints = new int[result.length + 1];
            for (int j = ints.length -1; j > 0;) {
                ints[j] = result[--j];
            }
            ints[0] = pow;
            result = ints;
        }
        return result;
    }

}
