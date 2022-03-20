package week04;

/**
 * 第四周作业三
 * 538. 把二叉搜索树转换为累加树
 * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 *
 *  利用 BST 的中序遍历特性
 *   BST 的中序遍历代码可以升序打印节点的值
 *   降序打印节点的值的话 递归顺序改一下就行了
 * 维护一个外部累加变量 sum，在遍历 BST 的过程中增加 sum，同时把 sum 赋值给 BST 中的每一个节点，就将 BST 转化成累加树了。
 * 但是注意顺序，正常的中序遍历顺序是 先左子树后右子树，这里需要反过来，先右子树后左子树
 */

public class Solution538 {
    public TreeNode convertBST(TreeNode root) {
        traverse(root);
        return root;
    }
    // 记录累加和
    int sum = 0;
    void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.right);
        // 维护累加和
        sum += root.val;
        // 将 BST 转化成累加树
        root.val = sum;
        System.out.println();
        traverse(root.left);
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}