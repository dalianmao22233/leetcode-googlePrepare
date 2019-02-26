/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode i = dummy;
        ListNode j = dummy;
        
        while (j.next != null) {  // j 一直到最后一个点
            j = j.next;         // 举例子1->2->9, 最后一次循环时， j指向了2， j=j.next, j指向了9.
            if (j.val != 9) {   // j指向9，i没动，还指向2.
                i = j;   
            }
        }
        
        if (j.val != 9) {   // 最后一个点小于9. 
            j.val++;
        } else {     // 最后一个点==9， 
            System.out.println(i.val);
            i.val++;
            System.out.println(i.val);
            i = i.next;
            while (i != null) {
                i.val = 0;   // 最后一位设置为0. （10）
                i = i.next;
            }
        }
        
        if (dummy.val == 0) {   // 证明最高位没有进位， 不是9->9->9的情况，是类似于1->2->9。
            return dummy.next;
        }
        return dummy;    // 是9->9->9的情况。
    }
}
