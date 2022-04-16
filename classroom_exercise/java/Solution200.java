import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution200 {
    public int numIslands(char[][] grid) {
        int ans = 0;
        row = grid.length;
        col = grid[0].length;

        visited = new boolean[row][col];
        boolean[] tmp = new boolean[col];
        Arrays.fill(tmp, false);
        Arrays.fill(visited, tmp);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ( grid[i][j] == '1' && !visited[i][j] ){
                    ans++;
                    bfs(grid, i, j);
                }
            }
        }


        return ans;
     }

    /**
     * 从起点出发  广度优先搜索
     * @param sx 起点横坐标
     * @param sy 起点纵坐标
     */
     void bfs(char[][] grid, int sx, int sy){
         Queue<Pair<Integer,Integer>> q = new ArrayDeque<Pair<Integer,Integer>>();
         // 方向数组
         int[] dx = new int[]{-1, 0, 0, 1};
         int[] dy = new int[]{0, -1, 1, 0};

         //bfs逻辑
         q.add(new Pair<>(sx,sy)); //起点入队
         visited[sx][sy] = true; //标记已走过
         while (!q.isEmpty()){
             // 第一步 取队头
             int x = q.peek().getKey();
             int y = q.peek().getValue();
             q.poll();

             // 第二步: 扩展队头
             for (int i = 0; i < 4; i++) {
                 int nx = x + dx[i];
                 int ny = y + dy[i];
                 if (nx < 0 || nx >= row || ny < 0 || ny >= col) continue;
                 if (grid[nx][ny] != '1') continue;
                 if (visited[nx][ny]) continue;
                 q.add(new Pair<>(nx, ny));
                 visited[nx][ny] = true;
             }
         }
     }


     int row;
     int col;
    // 是否走过
     private boolean[][] visited;

    public static void main(String[] args) {
        int row = 3;
        int col = 4;
        boolean[][] visited = new boolean[row][col];
        boolean[] tmp = new boolean[col];
        Arrays.fill(tmp, false);
        Arrays.fill(visited, tmp);
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                System.out.println(visited[i][j]);
            }
        }

    }
}
