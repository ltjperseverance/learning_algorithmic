package week10;

import java.util.ArrayList;
import java.util.List;

public class Solution699 {
    public List<Integer> fallingSquares(int[][] positions) {
        // 存放当前最大高度
        List<Integer> result = new ArrayList<>();
        // 存放当前方块的高度
        List<Integer> realHeight = new ArrayList<>();
        for (int i = 0; i < positions.length; i++) {
            int height = positions[i][1];
            int temp = 0;
            for (int j = 0; j < i; j++) {
                // 判断当前方块能否放在之前的方块上方，并取得最高值
                if (!(positions[j][0] >= (positions[i][0] + height)
                        || (positions[j][0] + positions[j][1]) <= positions[i][0])) {
                    temp = Math.max(temp, realHeight.get(j));
                }
            }
            realHeight.add(temp + height);
            result.add(Math.max(result.isEmpty() ? 0 : result.get(i - 1), temp + height));
        }
        return result;

    }
}
