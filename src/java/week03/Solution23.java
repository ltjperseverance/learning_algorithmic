package week03;

import java.util.PriorityQueue;

/**
 * 第三周作业一
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class Solution23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        // 保护节点
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        // 优先级队列，最小堆   怎么快速得到 k 个节点中的最小节点，接到结果链表上？
        // 这里我们就要用到 优先级队列（二叉堆） 这种数据结构，把链表节点放入一个最小堆，就可以每次获得 k 个节点中的最小节点
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                lists.length, (a, b)->(a.val - b.val));
        // 将 k 个链表的头结点加入最小堆
        for (ListNode head : lists) {
            if (head != null)
                pq.add(head);
        }

        while (!pq.isEmpty()) {
            // 获取最小节点，接到结果链表中
            ListNode node = pq.poll();
            p.next = node;
            if (node.next != null) {
                pq.add(node.next);
            }
            // p 指针不断前进
            p = p.next;
        }
        return dummy.next;
    }


    /**
     * 解法二
     * 借助优先队列特性 二叉堆
     */
    private PriorityQueue<Node> q = new PriorityQueue<>((a, b)->(a.key - b.key)); // 使用Lambda 确定比较方式  这样自定义类就不用实现Comparable接口
    public ListNode mergeKLists2(ListNode[] lists) {
        for (ListNode list : lists) {
            if (list == null) continue;
            q.add(new Node(list.val,list));
        }
        ListNode head = new ListNode(0,null);
        ListNode tail = head;
        while (!q.isEmpty()){
            Node node = q.peek();
            q.remove();
            tail.next = node.listNode;
            tail = tail.next;
            ListNode next = node.listNode.next;
            if (next != null){
                q.add(new Node(next.val,next));
            }

        }
        return head.next;
    }


}
class Node{
    int key;
    ListNode listNode;

    public Node(int key, ListNode listNode) {
        this.key = key;
        this.listNode = listNode;
    }

}


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
