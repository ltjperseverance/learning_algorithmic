package week09;

/**
 * 58. 最后一个单词的长度
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
 *
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 *
 */
public class Solution58 {
    public int lengthOfLastWord(String s) {
        int n = s.length(), ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) != ' '){
                ans++;
            }else {
                if (ans > 0){
                    break;
                }
            }
        }
        return ans;
    }
}
