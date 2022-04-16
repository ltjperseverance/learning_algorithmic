import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Solution23 {
//    private PriorityQueue<Node> q = new PriorityQueue<>();
    private BinaryHeap q = new BinaryHeap();
    public ListNode mergeKLists(ListNode[] lists) {
        for (ListNode list : lists) {
            if (list == null) continue;
            q.push(new Node(list.val,list));
        }
        ListNode head = new ListNode(0,null);
        ListNode tail = head;
        while (!q.isEmpty()){
            Node node = q.pop();
            tail.next = node.listNode;
            tail = tail.next;
            ListNode next = node.listNode.next;
            if (next != null){
                q.push(new Node(next.val,next));
            }

        }
        return head.next;
    }


}
class Node implements Comparable<Node>{
    int key;
    ListNode listNode;

    public Node(int key, ListNode listNode) {
        this.key = key;
        this.listNode = listNode;
    }

    @Override
    public int compareTo(Node o) {
        return this.key - o.key;
    }
}

/**
 * 自定义实现二叉堆
 */
class BinaryHeap{
    private ArrayList<Node> heap;
    public BinaryHeap() {
        heap = new ArrayList<>();
        //从1开始存，0位置存一个无意义的值
        heap.add(new Node(0,null));
    }
    boolean isEmpty(){
        return heap.size() == 1;
    }

    void push(Node node){
        // 插入先放到末尾  再往上调整 保持父子节点单调性
        heap.add(node);
        heapifyUp(heap.size()-1);
    }

    Node pop(){
        Node ans = heap.get(1);
        // 末尾交换到头部，然后删除末尾
        Collections.swap(heap, 1, heap.size() - 1);
        heap.remove(heap.size() - 1);
        // 向下调整
        heapifyDown(1);
        return ans;
    }

    /**
     * 向下调整
     * @param p
     */
    private void heapifyDown(int p){
        int child = p * 2; // 要换的孩子
        while (child < heap.size()){
            int other = p * 2 + 1;  // 另一个孩子
            // 先比较两个孩子，谁小就继续跟p比较
            // child存较小的孩子
            if (other < heap.size() && heap.get(other).key < heap.get(child).key) child = other;
            if (heap.get(child).key < heap.get(p).key) { // 小根堆
                Collections.swap(heap,child,p);
                p = child;
                child = p * 2;
            }else {
                break;
            }

        }
    }

    /**
     * 向上调整
     * @param p 指针indes   最开始指向数组末尾
     */
    private void heapifyUp(int p){
        while (p > 1) {
            if (heap.get(p).key < heap.get(p/2).key) { // 小根堆
                Collections.swap(heap,p,p/2);
                p /= 2;
            }else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> heap = new ArrayList<>();
        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.add(4);
        System.out.println(heap);
        heap.add(1,5);
        System.out.println(heap);
        heap.remove(heap.size()-1);
        System.out.println(heap);
    }

}
