// laicode

public class Solution {
  public List<List<Integer>> combinations(int target, int[] coins) {
    // Write your solution here
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (target < 0 || coins == null || coins.length == 0) {
      return res;
    }
    helper(target, coins, 0, new ArrayList<Integer>(), res);
    return res;
  }
  private void helper(int target, int[] coins, int level, List<Integer> ans, List<List<Integer>> res) {
    if (level == coins.length) {
      if (target == 0) {
        res.add(new ArrayList<>(ans));
      }
      return;
    }
    int max = target/coins[level];
    for (int i = 0; i <= max; i++) {
       ans.add(i);
       helper(target-coins[level]*i, coins, level+1, ans, res);
       ans.remove(ans.size()-1);
    }
  }
}
