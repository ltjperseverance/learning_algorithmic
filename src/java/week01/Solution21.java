package week01;

/**
 * 第一周作业二
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 */
public class Solution21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode last = new ListNode(0);
        ListNode cur = last;
        while (list1 != null || list2 != null){
            if (list1 != null && (list2 == null || list1.val <= list2.val)) {
                cur.next = list1;
                cur = list1;
                list1 = list1.next;
            }else {
                cur.next = list2;
                cur = list2;
                list2 = list2.next;
            }

        }
        return last.next;
    }
}

 class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
