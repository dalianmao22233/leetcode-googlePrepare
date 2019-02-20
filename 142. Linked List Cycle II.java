1. floyd算法： https://www.youtube.com/watch?v=LUm2ABqAs1w  
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) { // 此时确认有cycle，需要去找node在哪里
                ListNode search = head;
                while (search != slow) { //记住这个方法 肯定能找到起点
                    slow = slow.next;
                    search = search.next;
                }
                return slow;
            }
        }
        return null;
    }
}

2. hashtable
public class Solution {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<ListNode>();

        ListNode node = head;
        while (node != null) {
            if (visited.contains(node)) {
                return node;
            }
            visited.add(node);
            node = node.next;
        }

        return null;
    }
}
