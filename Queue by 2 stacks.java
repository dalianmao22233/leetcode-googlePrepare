// laioffer


public class Solution {
  private Deque<Integer> in;
  private Deque<Integer> out;
  
  public Solution() {
    // Write your solution here.
    in = new LinkedList<Integer>();
    out = new LinkedList<Integer>();
  }
  
  public Integer poll() {
    // 如果out空了，把in都放到out中
    // 是符合queue性质的：举例子就懂了，
    // stream: 1,2,3,4,5
    // in// 1,2,3,4,5
    // after move: out// 5,4,3,2,1, 此时pollFirst()是1. 符合先进先出。
    move();
    return out.isEmpty() ? null : out.pollFirst();
  }
  
  public void offer(int element) {
    in.offerFirst(element);
  }
  
  public Integer peek() {
    move();
    return out.isEmpty() ? null : out.peekFirst();
  }
  
  public int size() {
    return in.size() + out.size();
  }
  
  public boolean isEmpty() {
    return in.isEmpty() && out.isEmpty();
    // in.size() == 0 && out.size() == 0 is fine.
  }
  
  private void move() {
    if (out.isEmpty()) {
      while (!in.isEmpty()) {
        out.offerFirst(in.pollFirst());
      }
    }
  }
}
