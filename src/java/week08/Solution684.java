package week08;

/**
 * 684. 冗余连接
 * 树可以看成是一个连通且 无环 的 无向 图。
 *
 * 给定往一棵 n 个节点 (节点值 1～n) 的树中添加一条边后的图。添加的边的两个顶点包含在 1 到 n 中间，且这条附加的边不属于树中已存在的边。图的信息记录于长度为 n 的二维数组 edges ，edges[i] = [ai, bi] 表示图中在 ai 和 bi 之间存在一条边。
 *
 * 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组 edges 中最后出现的边。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/redundant-connection
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 解题思路
 * 并查集
 *初始 MakeSet  每个节点都的父节点为自己 fa[i]=i
 *遍历所有边  判断边的两个端点点是否属于同一个集合  是 为答案，返回边
 *                                         否 union  合并集合
 * 套用合并集模板
 * 实现find,unionSet方法
 *
 * 主方法逻辑 先 MakeSet
 * 再遍历每条边
 * if(fa[x] != fa[y]) unionSet(x,y);
 * else return edge;
 * 如果循环完还没有找到这样的边
 * 返回空数组
 *
 */
public class Solution684 {
    int[] fa;
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        // MakeSet
        fa = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            fa[i] = i;
        }
        for (int i = 0; i < n; i++) {
            int[] edge = edges[i];
            int x = edge[0], y = edge[1];
            if (find(x) != find(y)) {
                unionSet(x,y);
            } else {
                return edge;
            }
        }
        return new int[0];
    }

    public void unionSet(int x, int y) {
        x = find(x);
        y = find(y);
        // if (x != y) fa[x] = y;
        fa[x] = y;
    }

    public int find(int x) {
        if (x == fa[x]) {
            return x;
        }
        return fa[x] = find(fa[x]);
    }
}
