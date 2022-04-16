package week04;

import java.util.Stack;

/**
 * 第四周作业一
 * 130. 被围绕的区域
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 *
 * 解题思路
 *题目中解释说被包围的区间不会存在于边界上，所以我们会想到边界上的 O 要特殊处理，只要把边界上的 O 特殊处理了，那么剩下的 O 替换成 X 就可以了。
 * 问题转化为，如何寻找和边界联通的O
 *
 * 如何寻找和边界联通的O? 从边界出发，对图进行 dfs 和 bfs 即可.
 *  1、 bfs 递归。可以想想二叉树中如何递归的进行层序遍历。
 *  2、 bfs 非递归。一般用队列存储。
 *  3、 dfs 递归。最常用，如二叉树的先序遍历。
 *  4、 dfs 非递归。一般用 stack。
 *
 *  本地我们使用第四种方式
 *      1、边界判断  1、棋盘的行数或者列数小于3的时候所有的元素都是边上的元素
 *              2、row=0 || row = m -1 || col = 0 || col = n - 1  (m*n)
 *
 *      2、处理与边界O相连的O（用#替换） --dfs
 *
 *      3、处理剩下的o
 *
 *      4、处理# 替换回O
 */
public class Solution130 {
    int m,n;
    public void solve(char[][] board) {
        //棋盘的行数或者列数小于3的时候所有的元素都是边上的元素
        if (board == null || board.length < 3 || board[0].length < 3) return;
        m = board.length;
        n = board[0].length;
        if (m ==  0) return;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 从边缘第一个是o的开始搜索
                boolean isEdge = (i == 0 || j == 0 || i == m - 1 || j == n - 1);
                if (isEdge && board[i][j] == 'O') {  // 是边界 且值为O
                    dfs(board, i, j);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 处理剩下的o
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                // 处理# 替换回O
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

//    public void dfs(char[][] board, int i, int j) {
//        // 方向数组
//        int[] dx = {-1, 0, 1, 0};
//        int[] dy = {0, 1, 0, -1};
//        Stack<int[]> stack = new Stack<>();
//        stack.push(new int[]{i,j});// 入栈
//        board[i][j] = '#';  // 将O替换为#
//        while (!stack.isEmpty()) {  // 只要栈不为空  扩充
//            int[] current = stack.pop();  // 出栈
//            for(int k = 0; k<4; k++){
//                int x = current[0] + dx[k]; //往前后左右扩张
//                int y = current[1] + dy[k];
//                // 坐标未出界 且当前位置值为O时  将O替换为#
//                if(x>0 && x<board.length && y>0 && y<board[0].length && board[x][y] == 'O'){  // 符合条件  替换  入栈
//                    board[x][y] = '#';
//                    stack.push(new int[]{x, y});
//                }
//            }
//        }
//    }

    /**
     * 递归
     * @param board
     * @param x
     * @param y
     */
    public void dfs(char[][] board, int x, int y){
        if(x < 0 || x >= m || y < 0 || y >= n || board[x][y] != 0){
            return;
        }
//        对于所有的O标记为#
        board[x][y] = '#';

        //递归四周
        dfs(board, x + 1, y);
        dfs(board, x - 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
    }
}
