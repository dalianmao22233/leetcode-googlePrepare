my solution:
1. find mid
2. reverse后面一半
3. 两个list进行比较，如果有不相同的就肯定不是回文。

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        ListNode mid = reverse(findmid(head));
        while (head != null && mid != null) {
            if (head.val != mid.val) {
                return false;
            } else {
                head = head.next;
                mid = mid.next;
            }
        }
        return true;
    }
    public ListNode findmid(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while (fast != null && fast.next != null) {  //取中间两个点中靠后的一个，保证偶数个点的list能均匀分成2个list.
            prev = slow;    // 用prev保存slow， 到最后其实就是前面一半list的tail. 用于收尾。
            slow = slow.next;
            fast = fast.next.next;
        }
        if (prev != null) {
            prev.next = null;
        }
        return slow;
    }
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode cur = head;
        ListNode next = head;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
