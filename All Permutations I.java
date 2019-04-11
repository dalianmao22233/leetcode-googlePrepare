public class Solution {
  public List<String> permutations(String set) {
    // Write your solution here
    List<String> res = new ArrayList<String>();
    if (set == null) {
      return res;
    }
    if (set.length() == 0) {
      res.add("");
      return res;
    }
    char[] str = set.toCharArray();
    helper(str, res, 0);
    return res;
  }
  private void helper(char[] str, List<String> res, int index) {
    if (index == str.length) {
      res.add(new String(str));   // 注意这里不要用循环attach啦，这种真的好！
      return;
    }
    for (int i = index; i < str.length; i++) {
      swap(str, i, index);
      helper(str, res, index+1);
      swap(str, i, index);
    }
  }
   private void swap(char[] str, int i, int j) {
     char temp = str[i];
     str[i] = str[j];
     str[j] = temp;
   } 
}
