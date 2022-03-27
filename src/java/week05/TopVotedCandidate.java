package week05;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 力扣（LeetCode） 911. 在线选举
 * 给你两个整数数组 persons 和 times 。在选举中，第 i 张票是在时刻为 times[i] 时投给候选人 persons[i] 的。
 *
 * 对于发生在时刻 t 的每个查询，需要找出在 t 时刻在选举中领先的候选人的编号。
 *
 * 在 t 时刻投出的选票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候选人将会获胜。
 *
 * 实现 TopVotedCandidate 类：
 *
 * TopVotedCandidate(int[] persons, int[] times) 使用 persons 和 times 数组初始化对象。
 * int q(int t) 根据前面描述的规则，返回在时刻 t 在选举中领先的候选人的编号。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/online-election
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 */
class TopVotedCandidate {
    List<int[]> list = new ArrayList<>();
    public TopVotedCandidate(int[] persons, int[] times) {
        int val = 0;  // 记录当前最高票数
        Map<Integer, Integer> map = new HashMap<>(); // persons[i]候选人在i时刻的累计票数
        for (int i = 0; i < times.length; i++) {
            map.put(persons[i], map.getOrDefault(persons[i], 0) + 1); // persons[i] 有票 更新 （先取出 + 1） ; 无票 得 1票
            if (map.get(persons[i]) >= val) {  // 如果当前候选人票数 大于等于val
                val = map.get(persons[i]);     // 更新 val
                list.add(new int[]{times[i], persons[i]});   // 更新当前时刻 最优候选人
            }
        }
    }
    public int q(int t) {
        int l = 0, r = list.size() - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (list.get(mid)[0] <= t) l = mid;   // 符合条件  取右边
            else r = mid - 1;
        }
        return list.get(r)[0] <= t ? list.get(r)[1] : 0;
    }
}

