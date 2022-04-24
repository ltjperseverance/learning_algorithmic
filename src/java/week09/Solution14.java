package week09;

/**
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 长公共前缀的长度不会超过字符串数组中的最短字符串的长度。
 * 用minLength 表示字符串数组中的最短字符串的长度，则可以在 [0,minLength] 的范围内通过二分查找得到最长公共前缀的长度
 *
 * 每次取查找范围的中间值mid，判断每个字符串的长度为mid 的前缀是否相同，
 *  如果相同则最长公共前缀的长度一定大于或等于mid，
 *  如果不相同则最长公共前缀的长度一定小于mid
 *

 */
public class Solution14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        int left = 0, right = minLength;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            // [left,mid] 前缀相同   [mid,right]
            if (isCommonPrefix(strs, mid)) {
                left = mid;
            // [left,mid] 前缀不同   [left,mid - 1]
            } else {
                right = mid - 1;
            }
        }
        return strs[0].substring(0, left);
    }

    /**
     * 判断字符串长度len的前缀是否相同
     * @param strs
     * @param length
     * @return
     */
    public boolean isCommonPrefix(String[] strs, int length) {
        String str0 = strs[0].substring(0, length);
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            String str = strs[i];
            for (int j = 0; j < length; j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
