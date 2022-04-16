import java.util.*;

/**
 * 429. N 叉树的层序遍历
 */
public class Solution429 {
    public List<List<Integer>> levelOrder(Node root) {
        Queue<Pair> q = new LinkedList<>();
        List<List<Integer>> seq = new ArrayList<>();
        if (root == null) return seq;
        q.add(new Pair(root,0));
        while (!q.isEmpty()){
            Node node = q.peek().getNode();
            Integer depth = q.poll().depth;
            if(depth >= seq.size()) seq.add(new ArrayList<Integer>());
            seq.get(depth).add(node.val);
            for (Node child: node.children) {
                q.add(new Pair(child, depth + 1));
            }
        }
        return seq;
    }

    private class Pair{
        private Node node;
        private Integer depth;

        public Pair() {
        }

        public Pair(Node node, Integer depth) {
            this.node = node;
            this.depth = depth;
        }

        public Node getNode() {
            return node;
        }

        public void setNode(Node node) {
            this.node = node;
        }

        public Integer getDepth() {
            return depth;
        }

        public void setDepth(Integer depth) {
            this.depth = depth;
        }
    }
    private class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}

