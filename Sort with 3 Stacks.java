// laioffer
// 1. 孙老师做法：

public class Solution {
  public void sort(LinkedList<Integer> s1) {
  
  // 还可以写成：
 ## // Deque<Integer> s = new LinkedList<>();  因为LinkedList(以及ArrayDeque)是Deque接口的实现。
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

