
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution239 {
    private PriorityQueue<Pair> q = new PriorityQueue<>();
    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            q.add(new Pair(nums[i],i));
            if (i >= k - 1){
                /**
                 * 懒惰删除法
                 * 指的是需要从堆中删除一个元素（不一定是最大值）时，不直接删除，而是打上删除标记(soft delete)
                 * 等未来它作为最大值被取出时，再判断它是否已经被标记，若是则抛弃它，取下一个最大值
                 *
                 * "懒惰"的含义——只要要删除的元素还不是最大值，在堆里多待一会儿也无妨，未来等它会影响最大值正确性的时候再说吧
                 */
                while (q.peek().index <= i - k) q.poll();  // 当堆顶 索引无效时  删除堆顶
                ans.add(q.peek().key);
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    /**
     * 存入优先队列 自定义累需要实现Comparable接口
     * 由于Java中_PriorityQueue_实现了_Queue_接口，不允许放入null元素；
     * 其通过堆实现，具体说是通过完全二叉树（complete binary tree）实现的小顶堆（任意一个非叶子节点的权值，都不大于其左右子节点的权值）
     *
     * 默认情况下从小到大排序  堆顶是最小值  但是本题目需要求最大值
     * 所以compareTo方法要倒排
     */
    class Pair implements Comparable<Pair>{
        Integer key;  //关键码
        Integer index; //下标

        public Pair(Integer key, Integer index) {
            this.key = key;
            this.index = index;
        }

        @Override
        public int compareTo(Pair o) {
            return o.key - this.key;
        }
    }
}
