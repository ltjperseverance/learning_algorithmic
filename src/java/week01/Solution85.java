package week01;

import java.util.Stack;

/**
 * 第一周作业四
 * 85. 最大矩形
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 */
public class Solution85 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int[] heights = new int[matrix[0].length];
        int maxArea = 0;
        for (int row = 0; row < matrix.length; row++) {
            //遍历每一列，更新高度
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == '1') {
                    heights[col] += 1;
                } else {
                    heights[col] = 0;
                }
            }
            //调用上一题的解法，更新函数
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }
    public int largestRectangleArea(int[] heights) {
        Stack<Rect> s = new Stack();
        int ans = Integer.MIN_VALUE;
        int[] newHeights = new int[heights.length + 1];
        for (int i = 0; i < heights.length; i++) {
            newHeights[i] = heights[i];
        }
        newHeights[newHeights.length - 1] = 0;
        for (int newHeight : newHeights) {
            int curWidth = 0;
            while (!s.empty() && s.peek().getHeight() >= newHeight) {
                int curHeight = s.peek().getHeight();
                curWidth += s.peek().getWidth();
                ans = Math.max(ans, curWidth*curHeight);
                s.pop();
            }

            s.push(new Rect(newHeight,(curWidth + 1)));
        }

        return ans;
    }
    class Rect{
        int height;
        int width;
        public Rect(int height, int width) {
            this.height = height;
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}
