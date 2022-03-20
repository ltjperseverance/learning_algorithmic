package week03;

/**第三周第五题
 * 685. 冗余连接 II
 *
 *
 */
public class Solution685 {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int nodes = edges.length;
        //edges 的节点编号是从1开始的，所以要传入 nodes + 1
        UnionFind unionFind = new UnionFind(nodes + 1);
        int[] parent = new int[nodes + 1];
        for (int i = 1; i <= nodes; i++) {
            parent[i] = i;
        }
        //表示冲突和有环的两种情况，因为这是有向图
        //无冲突有环，节点循环指向，如 1-->2，2-->3，3-->1
        //有冲突无环，某节点入度大于1，如 1-->2，1-->3，2-->3
        //有冲突有环，如 1-->2，2-->3，3-->1，4-->2
        int conflict = -1;
        int cycle = -1;

        for (int i = 0; i < nodes; i++) {
            //当前边
            int[] edge = edges[i];
            //node1 --> node2
            int node1 = edge[0], node2 = edge[1];
            //第一次有 node1' 指向 node2 时，parent[node2] 肯定为 node2，并进入到 else 使 parent[node2] = node1'
            //第二次又有 node1" 指向 node2 时，因为第一次就已经更改 parent[node2]，故必不为 node2
            //故可以判断出 node2 入读 大于1，有冲突，冗余的冲突的哪条都行，为方便，我们选择当前边 edges[i]
            if (parent[node2] != node2) {
                conflict = i;
            } else {
                //parent[i] = j，表示 节点i 的入度来自 节点j，即j --> i，以树的角度就是 i 的父节点为 j
                parent[node2] = node1;
                //如果 node1 和 node2 已经在同一个集合里，那么 node1 --> node2 这条边将即可构成环
                if (unionFind.find(node1) == unionFind.find(node2)) {
                    cycle = i;
                } else {
                    //如果 node1 和 node2 不在同一集合中，那么 node1 --> node2 这条边将使得它俩在一个集合中
                    unionFind.union(node1, node2);
                }

            }
        }

        //情况1：无冲突有环，冗余边为使图成环的那条边
        if (conflict < 0) {
            int[] redundant = {edges[cycle][0], edges[cycle][1]};
            return redundant;
        } else {
            //情况2：有冲突
            int[] conflictEdge = edges[conflict];
            if (cycle >= 0) {
                //情况2.1：有冲突有环，冗余边为使冲突且成环的那条边
                //conflictEdge[1] 即冲突点，其入度大于1
                //由于每个节点的 parent 只有一次赋予机会，故 parent[conflictEdge[1]] 为环内点（为什么呢？？）
                int[] redundant = {parent[conflictEdge[1]], conflictEdge[1]};
                return redundant;
            } else {
                //情况2.2：有冲突无环，冗余边为使图冲突的那条边
                int[] redundant = {conflictEdge[0], conflictEdge[1]};
                return redundant;
            }

        }

    }
}

//实现并查集的实例，用数组表示 （集）
//下标 i 表示元素 i，ancestor[i] 表示所属集合，所属集合用代表元素 j 表示
//有合并 元素 i1 和 元素 i2 的 union 功能 （并）
//和查找 元素 i 属于哪个集合 的 find 功能 （查）

class UnionFind {
    int[] ancestor;

    public UnionFind(int n) {
        ancestor = new int[n];
        //初始化时，每个元素都为独立的节点集合
        for (int i = 0; i < n; i++) {
            ancestor[i] = i;
        }
    }

    /**
     * 并
     * 合并 idx1 所在集合，及 idx2 所在集合
     * 将前者所在集合的代表元素的值，定义为后者所在集合的代表元素
     * @param idx1
     * @param idx2
     */
    public void union(int idx1, int idx2) {
        ancestor[find(idx1)] = find(idx2);
    }

    /**
     * 查
     * 查找元素 idx 所在的集合，以集合代表元素表示
     * @param idx
     * @return 元素 idx 所在集合的代表元素
     */
    public int find(int idx){
        //层层递推直到集合的代表元素（或者说根）
        if (ancestor[idx] != idx){
            ancestor[idx] = find(ancestor[idx]);
        }

        return ancestor[idx];
    }
}
