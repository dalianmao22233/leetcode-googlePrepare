很清楚明白，先取中点，然后reverse后半截，merge两段list

class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode mid = reverse(findMid(head));
        merge(head, mid);
        
    }
    public void merge(ListNode l1, ListNode l2) {
        while (l2 != null) {
            ListNode next = l1.next;
            l1.next = l2;
            l1 = l2;
            l2 = next;
        }
        // 交替着merge. 不断挂在l1的后面
    }
    
    private void merge(ListNode a, ListNode b) {  // 我写的这种merge,也是对的 
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (a != null && b != null) {
            tail.next = a;
            a = a.next;
            tail = tail.next;
            tail.next = b;
            b = b.next;
            tail = tail.next;
        }
        
        if (a != null) {
            tail.next = a;
        } else {
            tail.next = b;
        }
    }
    
    
    public ListNode findMid(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode prev = head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) { 
            // 偶数个点时，这个条件可以取到中间两个点的第二个点。 如果是fast.next != null && fast.next.next != null, 则是取第一个点
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (prev != null) {   // 注意加上判断！
            prev.next = null;   //注意加上这个，前半段才算结束，prev实际上就是tail of 前半截
        }
        return slow;
    }
    public ListNode reverse(ListNode l) {
        ListNode cur = l;
        ListNode prev = null;
        ListNode next = l;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
