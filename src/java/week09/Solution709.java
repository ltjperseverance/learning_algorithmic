package week09;

import java.util.List;

/**
 * 709. 转换成小写字母
 * 给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。
 */
public class Solution709 {
    public String toLowerCase(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z'){
//                chars[i] = (char) (chars[i] - 'A' + 'a');
                chars[i] = chars[i] |= 32;
            }
        }
        return new String(chars);

    }
}
