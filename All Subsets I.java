// laicode

public class Solution {
  public List<String> subSets(String set) {
    // Write your solution here.
    List<String> res = new ArrayList<String>();
    if (set == null) {
      return res;
    }
    if (set.length() == 0) {
      res.add("");
      return res;
    }
    char[] str = set.toCharArray();
    helper(str, res, new StringBuilder(), 0);
    return res;
  }
  private void helper(char[] str, List<String> res, StringBuilder sb, int index) {
    if (index == str.length) {
      res.add(sb.toString());
      return;
    }
    
    sb.append(str[index]);
    helper(str, res, sb, index+1);
    sb.deleteCharAt(sb.length()-1);
    
    helper(str, res, sb, index+1);   // 选或不选， 这两段颠倒顺序也没问题。
  }
}
