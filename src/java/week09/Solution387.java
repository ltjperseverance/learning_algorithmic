package week09;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 387. 字符串中的第一个唯一字符
 * 给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。
 */
public class Solution387 {
    public int firstUniqChar(String s) {
        Map<Character,Integer> cc = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (cc.containsKey(s.charAt(i))){
                cc.put(s.charAt(i),cc.get(s.charAt(i)) + 1);
            }else {
                cc.put(s.charAt(i),1);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (cc.get(s.charAt(i)) == 1){
                return i;
            }
        }
        return -1;
    }

}
