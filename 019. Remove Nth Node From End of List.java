1、 1 pass，不用都遍历一遍。 保证first和second指针之间有n个节点就可以了
1,2,3,4,5,6,7,8   n=2

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode first = dummy;
    ListNode second = dummy;
    // Advances first pointer so that the gap between first and second is n nodes apart
    for (int i = 1; i <= n + 1; i++) {
        first = first.next;
    }
        //System.out.println(first.val);    first.val = 3
    // Move first to the end, maintaining the gap
    while (first != null) {
        first = first.next;
        second = second.next;  
    }
       // System.out.println( second.val);  second.val = 6
       
       // 此时second指向的是要被删除的node的前一个，所以才能直接移除node.
    second.next = second.next.next;
    return dummy.next;
}
}


2. 2 pass
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    int length  = 0;
    ListNode first = head;
    while (first != null) {
        length++;
        first = first.next;
    }
    length -= n;
    first = dummy;
    while (length > 0) {
        length--;
        first = first.next;
    }
    first.next = first.next.next;
    return dummy.next; // eg: [1], n=1, after operation it is [], then return head is wrong.
}
