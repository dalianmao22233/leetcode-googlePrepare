1. 常规解法，2 pointer
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
            i.val++;
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

2. recursive：

public ListNode plusOne(ListNode head) {
    if( DFS(head) == 0){
        return head;
    }else{
        ListNode newHead = new ListNode(1);
        newHead.next = head;
        return newHead;
    }
}

public int DFS(ListNode head){
    if(head == null) return 1;
    
    int carry = DFS(head.next);
    
    if(carry == 0) return 0;
    
    int val = head.val + 1;
    head.val = val%10;
    return val/10;
}
