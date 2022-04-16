import java.util.*;

public class Solution51 {
    private int n;
    boolean[] used;
//    boolean[] usedPlus;
    HashMap<Integer,Boolean> usedPlus;
//    boolean[] usedMinus;  //相减 索引可能为负  使用map
    HashMap<Integer,Boolean> usedMinus;
    LinkedList<Integer> p;
    List<List<Integer>> ans;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        ans = new ArrayList<List<Integer>>();
        p = new LinkedList<>();
        used = new boolean[n];
        usedPlus = new HashMap<>(n);
        Arrays.fill(used,false);
        usedMinus = new HashMap<>(n);

        ArrayList<List<String>> result = new ArrayList<>();
        dfs(0);

        ArrayList<String> board  = new ArrayList<>();
        for(List<Integer> q : ans) {
            char[] pattern = new char[n];
            Arrays.fill(pattern, '.');
            for (int row = 0; row < n; row++){
                pattern[q.get(row)] = 'Q';
            }
            board.add(new String(pattern));
            result.add(board);
        }
        return result;
    }

    private void dfs(int row){
        if ( row == n && p!= null && p.size()>0){
            ans.add(p);
            return;
        }

        for ( int col = 0; col < n; col++ ){
            if(!usedPlus.containsKey(row + col)) usedPlus.put(row + col, false);
            if(!usedMinus.containsKey(row - col)) usedMinus.put(row - col,false);

            if (!used[col] && !usedPlus.get(row + col) && !usedMinus.get(row - col)) {
                p.addLast(col);
                used[col] = true;
                usedPlus.put(row + col,true); //考虑 / 对角线  和相等
                usedMinus.put(row - col, true) ; //考虑 \ 对角线  差相等
                dfs(row + 1);
                used[col] = false;
                usedPlus.put(row + col, false);
                usedMinus.put(row - col, false);
                p.removeLast();
            }
        }
    }


}
