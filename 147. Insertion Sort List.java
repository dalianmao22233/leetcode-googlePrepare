题目已经给了算法步骤：
Algorithm of Insertion Sort:

1.Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list. 每次都拿出来一个 插入到新链表
2.At each iteration, insertion sort removes one element from the input data, 
  finds the location it belongs within the sorted list, and inserts it there.
3.It repeats until no input elements remain.


链表的插入排序实现原理很简单，就是一个元素一个元素的从原链表中取出来，然后按顺序插入到新链表中，时间复杂度为O(n^2)，
是一种效率并不是很高的算法，但是空间复杂度为O(1)，以高时间复杂度换取了低空间复杂度。
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);  // 这是新链表，先不和head有任何联系。
        
        ListNode cur = head;
        ListNode next = null;
        
        // 通过下面第一次while循环就可以将dummy和list中的点连接起来了。  
        while (cur != null) {
            next = cur.next;  // 先保存next节点
            ListNode p = dummy;  // 每一遍循环都要从头开始指
            while (p.next != null && p.next.val < cur.val) {
                p = p.next;
            }
            
            // 在p和p.next之间插入cur
            cur.next = p.next;
            p.next = cur;
            cur = next;
            
        }
        return dummy.next;
    }
}
