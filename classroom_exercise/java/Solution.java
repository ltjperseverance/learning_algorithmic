import java.util.*;

public class Solution {
//    public List<List<Integer>> fourSum(int[] nums, int target) {
//        Arrays.sort(nums);
//        Set<List<Integer>> s = new HashSet<>();
//        for (int i = 0; i < nums.length - 1; i++) {
//            for (int j = i+ 1; j < nums.length; j++) {
//                int left = j + 1, right= nums.length - 1;
//                while (left < right) {
//                    if (nums[i] + nums[j] + nums[left] + nums[right] == target){
//                        s.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
//                        left++;
//                        right--;
//                    }else if(nums[i] + nums[j] + nums[left] + nums[right] < target){
//                        left++;
//                    }else {
//                        right--;
//                    }
//                }
//            }
//        }
//        List<List<Integer>> ans = new ArrayList<>(s);
//        return ans;
//    }

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        m = maze.length;
        n = maze[0].length;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(visited[i],false);
        }
        return dfs(maze,start[0],start[1],destination);
    }
    boolean dfs(int[][] maze, int startRow, int startCol, int[] destination){
        if (visited[startRow][startCol]) return false;
        if (startRow == destination[0] && startCol == destination[1]) return true;
        visited[startRow][startCol] = true;
        int right = startCol + 1, left = startCol - 1, up = startRow - 1 , down = startRow + 1;
        // 从start向右走 遇墙停下
        while (right < n && maze[startRow][right] == 0) right++;
        // 判断是不是目标点
        if(dfs(maze,startRow,right - 1, destination))
            return true;
        // 向左走 遇墙停下
        while (left >= 0 && maze[startRow][left] == 0) left--;
        if (dfs(maze,startRow,left + 1,destination)) return true;

        while (up >= 0 && maze[up][startCol] == 0) up--;
        if (dfs(maze,up+1,startCol,destination)) return true;

        while (down < m && maze[down][startCol] == 0) down++;
        if (dfs(maze,down - 1, startCol,destination)) return true;

        return false;
    }
    private boolean[][] visited;
    private int m;
    private int n;

}
