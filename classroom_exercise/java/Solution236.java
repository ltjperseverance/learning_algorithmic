import javafx.util.Pair;

public class Solution236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.p = p;
        this.q = q;
        dfs(root);
        return ans;
    }

    private Pair<Boolean,Boolean> dfs(TreeNode root) {
        if (root == null) return new Pair<Boolean,Boolean>(false,false);
        Pair<Boolean,Boolean> left = dfs(root.left);
        Pair<Boolean,Boolean> right = dfs(root.right);

        Boolean key = left.getKey() || right.getKey() || root.val==q.val;
        Boolean value = left.getKey() || right.getKey() || root.val == p.val;
        Pair<Boolean,Boolean> result = new Pair<>(key,value);

        if (result.getKey() && result.getValue() && ans == null) ans = root;
        return result;
    }

    private TreeNode p;
    private TreeNode q;
    private TreeNode ans;

    private class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }


}
