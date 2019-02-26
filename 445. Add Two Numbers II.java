1. 类似于add two number I， 实际上就是换顺序，顺其自然想到stack. 
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        
        int sum = 0;
        ListNode dummy = new ListNode(0);
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (!s1.isEmpty()) {
                sum += s1.pop();
            }
            if (!s2.isEmpty()) {
                sum += s2.pop();
            }
            dummy.val = sum%10;
            ListNode head = new ListNode(sum/10); // carry
            head.next = dummy;
            dummy = head;   // dummy 始终指向最开始的头部。
            sum = sum/10;
        }
        // dummy.val == 0 ： 例子： 1+2 = 3. 此时dummy.val = sum%10 = 3,  head.val = sum/10 = 0。 经过后面2步转换，最后dummy.val = 0. head.val = 3. 这种就是没有进位的情况。 反之就是carry = 1.
        return dummy.val == 0 ? dummy.next : dummy;
    }
}
2. 啥也不用就愣加：说实话不太懂
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    int carry = 0;
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        
        if (l2 == null) {
            return l1;
        }
        ListNode res;
        
        int len1 = getLen(l1), len2 = getLen(l2);
        if (len1 < len2) {
            res = add(l1, l2, len2-len1);
        } else {
            res = add(l2, l1, len1-len2);
        }
        if (carry > 0) {
            ListNode res1 = res;
            res = new ListNode(carry);
            res.next = res1;
        }
        return res;
    }
    
    public int getLen(ListNode l) {
        int len = 0;
        while (l != null) {
            l = l.next;
            len++;
        }
        return len;
    }
    
    // l2.length - l1.length = diff
    public ListNode add(ListNode l1, ListNode l2, int diff) {
        if (l1.next == null && l2.next == null) {   // only 1 node in list.
            int sum = l1.val + l2.val;
            carry = sum/10;
            return new ListNode(sum%10);
        }
        ListNode res, next;
        int sum;
        if (diff == 0) {
            next = add(l1.next, l2.next, 0);
            sum = l1.val + l2.val + carry;
        } else {
            next = add(l1, l2.next, diff-1);
            sum = carry + l2.val;
        }
        carry = sum/10;
        res = new ListNode(sum%10);
        res.next = next;
        return res;
    }
}
