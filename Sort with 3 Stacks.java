// laioffer
// 1. 孙老师做法：

思想就是：
s1 // 4 3 1 2 5
s2 // 
s3 //

维护一个min, 每次找到s1中的min, 然后s1中所有元素放到s2中，
把s2的栈顶与min比较，如果是min, 就放到s3中，不是的话就放回s1.
执行以上2个操作直到s1为空。此时s3是有序的，从栈顶到栈底为降序。
把s3放回s1. 此时是升序的。


public class Solution {
  public void sort(LinkedList<Integer> s1) {
  
  // 还可以写成：
  // Deque<Integer> s = new LinkedList<>();  因为LinkedList(以及ArrayDeque)是Deque接口的实现。
    LinkedList<Integer> s2 = new LinkedList<Integer>();
    LinkedList<Integer> s3 = new LinkedList<Integer>();
    // Write your solution here.
    helper(s1, s2, s3);
  }
  
  private LinkedList<Integer> helper(LinkedList<Integer> s1, LinkedList<Integer> s2, LinkedList<Integer> s3) {
    if (s1 == null || s1.size() < 2) {
      return s1;
    }
    while (!s1.isEmpty()) {
      int min = s1.peekFirst();
      while (!s1.isEmpty()) {
        if (s1.peekFirst() < min) {
          min = s1.peekFirst();
        }
        s2.offerFirst(s1.pollFirst());
      }
      while (!s2.isEmpty()) {
        if (min == s2.peekFirst()) {
          s3.offerFirst(s2.pollFirst());
        } else {
          s1.offerFirst(s2.pollFirst());
        }
      }
    }
    while (!s3.isEmpty()) {
      s1.offerFirst(s3.pollFirst());
    }
    return s1;
  }
}


2. 严老师做法：

