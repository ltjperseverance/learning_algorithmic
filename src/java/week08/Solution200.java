package week08;

/**
 * 相邻的陆地（只需要向右看和向下看）合并，只要发生过合并，岛屿的数量就减少1；
 * 在遍历的过程中，同时记录空地的数量；
 * 并查集中连通分量的个数 - 空地的个数，就是岛屿数量。
 *
 */
public class Solution200 {
    private int rows;
    private int cols;

    public int numIslands(char[][] grid) {
        rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        cols = grid[0].length;

        // 空地的数量
        int spaces = 0;
        UnionFind unionFind = new UnionFind(rows * cols);
        // 方向数组
        int[][] directions = {{1, 0}, {0, 1}};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '0') {
                    spaces++;
                } else {
                    // 此时 grid[i][j] == '1'
                    for (int[] direction : directions) {
                        int ni = i + direction[0];
                        int nj = j + direction[1];
                        // 先判断坐标合法，再检查右边一格和下边一格是否是陆地
                        if (ni < rows && nj < cols && grid[ni][nj] == '1') {
                            unionFind.union(getIndex(i, j), getIndex(ni, nj));
                        }
                    }
                }
            }
        }
        return unionFind.getCount() - spaces;
    }

    private int getIndex(int i, int j) {
        return i * cols + j;
    }

    private class UnionFind {
        /**
         * 连通分量的个数
         */
        private int count;
        private int[] fa;

        public int getCount() {
            return count;
        }

        public UnionFind(int n) {
            this.count = n;
            fa = new int[n];
            for (int i = 0; i < n; i++) {
                fa[i] = i;
            }
        }

        private int find(int x) {
           if (x == fa[x]) {
               return x;
           }
           return  fa[x] = find(fa[x]);

        }

        public void union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x != y) {
                fa[x] = y;
                count--;
            }


        }
    }
}
