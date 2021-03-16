高级 太流畅了！

class Solution {
   public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;  // cur 存尾节点
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
        // 为什么要carry !=0 ： 
         //[9,9,9,9,9,9,9]
         //[9,9,9,9]
         //[8,9,9,9,0,0,0,1] 为正确结果，如果没有carry的条件，10/10=1, 最后一个1就没法得到。
            if (l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }

            cur.next = new ListNode(carry % 10);
            carry /= 10;
            cur = cur.next;
        }
        return dummy.next;
    }
}
