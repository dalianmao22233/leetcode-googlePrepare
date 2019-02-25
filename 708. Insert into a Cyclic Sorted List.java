https://leetcode.com/problems/insert-into-a-cyclic-sorted-list/discuss/149374/Java-5ms-One-Pass-and-Two-Pass-Traverse-With-Detailed-Comments-and-Edge-cases!
https://leetcode.com/problems/insert-into-a-cyclic-sorted-list/discuss/163141/Share-my-concise-JAVA-one-pass-solution

/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val,Node _next) {
        val = _val;
        next = _next;
    }
};
*/
class Solution {
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node newNode = new Node(insertVal, null);
            head = newNode;
            newNode.next = head;
            return head;
        }
        Node cur = head;
        while (cur.val <= cur.next.val && cur.next != head) {
            cur = cur.next;
        }
        Node max = cur; // find max value now.
        Node dummy = new Node(0, max.next);    // max.next = 最小值，dummy就指向了最小值，此时当做链表头部了。
        max.next = null; // 截断cycle. 最大值节点就在最后。
        cur = dummy; // cur指向头部
        while (cur.next != null && cur.next.val < insertVal) {
            cur = cur.next;
        }
        cur.next = new Node(insertVal, cur.next);  // 插入新节点了！
        Node newMax = max.next == null ? max : max.next; // 重新连接为cycle  , max.next==null 意味着没插到最后(max)， 插在中间了。
        newMax.next = dummy.next;
        return head;
    }
}
