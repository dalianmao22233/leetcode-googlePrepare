//laioffer

教案答案里有返回值是Integer的情况，此时注意return null.

public class Solution {
  private Deque<Integer> stack;
  private Deque<Integer> minStack;
  
  public Solution() {
    // write your solution here
    stack = new LinkedList<Integer>();
    minStack = new LinkedList<Integer>();
  }
  
  public int pop() {
    if (stack.isEmpty()) {
      return -1;
    }
    int res = stack.pollFirst();
    if (minStack.peekFirst() == res) {
      minStack.pollFirst();   
      // 如果当前minStack栈顶和stack栈顶是同一个值，说明都该pop出去。如果不是，则说明minStack存储的是stack之前的值（当前最小值）
    }
    return res;
  }
  
  public void push(int element) {
    stack.offerFirst(element);
    if (minStack.isEmpty() || minStack.peekFirst() >= element) {  //一定注意这里有个isEmpty的判断，如果没有会NPE
      minStack.offerFirst(element);
    }
  }
  
  public int top() {
    if (stack.isEmpty()) {
      return -1;
    }
    return stack.peekFirst();
  }
  
  public int min() {
    if (minStack.isEmpty()) {
      return -1;
    }
    return minStack.peekFirst();
  }
}
