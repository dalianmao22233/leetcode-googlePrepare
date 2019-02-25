https://leetcode.com/problems/insert-into-a-cyclic-sorted-list/discuss/149374/Java-5ms-One-Pass-and-Two-Pass-Traverse-With-Detailed-Comments-and-Edge-cases!
https://leetcode.com/problems/insert-into-a-cyclic-sorted-list/discuss/163141/Share-my-concise-JAVA-one-pass-solution


2. one pass:  http://www.cnblogs.com/grandyang/p/9981163.html
记这种，比较清楚。

class Solution {
    public Node insert(Node head, int x) {
        if (head == null) {
            Node newNode = new Node(x, null);
            head = newNode;
            newNode.next = head;
            return head;
        }
        
        // 只有以下几种情况：
        // 1. pre < x < cur, 这种直接break, 然后连接就完事了
        // 2. pre > cur, 并且： pre < x or x < cur, 其实就是x小于最小值，或者大于最大值，直接break, 然后连接就完事了
        // 1->2->3, pre指向3， cur指向1， x = 4. 这种直接出了while, 因为cur == head了。。。
        // 更好的例子：3->4->5->1->3, pre指向5， cur指向1， x = 6, 这种就break了。 直接连接。
        // 3. 都不符合，往后移动指针。
        Node pre = head;
        Node cur = pre.next;
        while (cur != head) {
            if (pre.val <= x && cur.val >= x) {
                break;
            }
            if (pre.val > cur.val && (pre.val <= x || cur.val >= x)) {
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        pre.next = new Node(x, cur);
        return head;
    }
}


1. discussion 做法
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


