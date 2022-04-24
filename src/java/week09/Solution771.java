package week09;

import java.util.HashSet;
import java.util.Set;

/**
 * 771. 宝石与石头
 * 给你一个字符串 jewels 代表石头中宝石的类型，另有一个字符串 stones 代表你拥有的石头。 stones 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 *
 * 字母区分大小写，因此 "a" 和 "A" 是不同类型的石头。
 *
 */
public class Solution771 {
    public int numJewelsInStones(String jewels, String stones) {
        int ans = 0;
        int n = jewels.length();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(jewels.charAt(i));
        }
        for(char ch : stones.toCharArray()){
            if (set.contains(ch)){
                ans++;
            }
        }
        return ans;
    }
}
