// '?' Matches any single character.  就是1对1的
// '*' Matches any sequence of characters (including the empty sequence).  0或多个字母都可以

// Input:
// s = "aa"
// p = "*"
// Output: true
// Explanation: '*' matches any sequence.
    
// https://www.youtube.com/watch?v=3ZDZ-N0EPV0
// DP规律就是
// T[i][j] = T[i-1][j-1],            if s[i] == p[j], or p[j] == '?'
//           T[i-1][j] || T[i][j-1], if p[j] == '*'
//           False
          
          
          
    
// 1. dp:

dp[i][j] 表示 s中前i个字符组成的子串和p中前j个字符组成的子串是否能匹配。
大小初始化为 (m+1) x (n+1)，加1的原因是要包含dp[0][0] 的情况，因为若s和p都为空的话，也应该返回true，所以也要初始化 dp[0][0] 为true。
还需要提前处理的一种情况是，当s为空，p为连续的星号时的情况。由于星号是可以代表空串的，所以只要s为空，
那么连续的星号的位置都应该为true，所以我们现将连续星号的位置都赋为true。

class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int lens = s.length();
        int lenp = p.length();
        boolean[][] dp = new boolean[lens+1][lenp+1];
        dp[0][0] = true; // s and p are empty.
        
        for (int i = 1; i <= lenp; i++) {
            if (p.charAt(i-1) == '*') {   // * 能match 所有。
                dp[0][i] = dp[0][i-1];
            }
        }
        for (int i= 1; i <= lens; i++) {
            for (int j = 1; j <= lenp; j++) {
                if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[lens][lenp];
    }
}
   


//优化：用滚动数组， 1维DP：

public boolean isMatch(String s, String p) {
    boolean[] dp = new boolean[s.length()+1];   // dp[i] maps to dp[i][j], where i is the length of s, j is the length of j
    char[] sStr = s.toCharArray();
    char[] pStr = p.toCharArray();
    dp[0] = true;   // before we started any iteration, dp[0] is equal to dp[0][0], meaning both s and p are EMPTY
    for (int j=1; j<=pStr.length; j++) {
        char pChr = pStr[j-1];
        if (pChr == '*') {
            // same optimization as complete backpack problem
            // we scan from 1 to s.length, so that dp[i] = dp[i] || dp[i-1]
            // just thinking like every time we iterating dp[i], the dp[i][j] we referenced is actually previous iteration's result. so dp[i-1] is actually dp[i-1][j] from previous step of current i-loop for string s, dp[i] is actually dp[i][j-1] from previous j-loop for string p
            // it's relatively easy to understand that before the dp[i]'s value is updated, dp[i] refers to dp[i][j-1] 
            // the hard to understand part may be why dp[i-1] equals to dp[i-1][j]. Notice dp[i-1]'s value was updated in previous step's update (we update dp[i-1] in dp[i-2]'s step), so dp[i-1] is equal to dp[i-1][j]
            for (int i=1; i<=sStr.length; i++) {
                dp[i] = dp[i] || dp[i-1];
            }
        } else {
            // same optimization as zero-one backpack
            // dp[i-1] means dp[i-1][j-1]
            for (int i=sStr.length; i>=1; i--) {
                if (sStr[i-1] == pChr || pChr == '?') {
                    dp[i] = dp[i-1];
                } else {
                    dp[i] = false;
                }
            }
            // now, after we see the first non-'*' character, as dp[0] means dp[i][0] where i>=1, dp[i][0] it's definite false (matching empty p with non-empty s)
            // we want to set that value to false
            dp[0] = false;
        }
    }
    return dp[sStr.length];
}



// 2. one pass
public boolean isMatch(String s, String p) {
        if(p == null || s == null)
            return false;
        int lens = s.length(), lenp = p.length();
        int i = 0, j = 0, pres= -1, prep = -1;
        while(i < lens) {
            // advancing both pointers
            if(j < lenp && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                i++;
                j++;
            }// * found, only advancing pattern pointer
            else if(j < lenp && p.charAt(j) == '*') {
                pres = i;
                prep = j;
                j++;
            }
            // last pattern pointer was *, advancing string pointer
            else if(pres != -1) {
                i = ++pres;
                j = prep + 1;
            }
            //current pattern pointer is not star, last patter pointer was not *
          //characters do not match
            else return false;
        }
        //check for remaining characters in pattern， 如果剩余的字母不是*意味着不能匹配。因为p中不能出现s中没出现过的字符。
        while(j < lenp && p.charAt(j) == '*') {
            j++;
        }
        return j == lenp;
    }
    
// 2. dfs+memo
public boolean isMatch(String s, String p) {
        int[][] memo = new int[s.length()][p.length()];
        return helper(s, p, s.length()-1, p.length()-1, memo);     
}

public boolean helper(String s, String p, int i, int j, int[][] memo) {
    if(i<0&&j>=0) {
        while(j>=0&&p.charAt(j)=='*') 
            j--;
        return j==-1;
    }else if(i<0&&j<0)
        return true;
    else if(i>=0&&j<0)
        return false;
    else{
        if(memo[i][j]!=0) return memo[i][j]==1;

        char sc = s.charAt(i);
        char pc = p.charAt(j);
        if(pc!='*'&&pc!='?')
        {
            if(sc!=pc)
            {
                memo[i][j]=-1;
                return false;
            }
            else
                return helper(s, p, i-1, j-1, memo);
        }else if(pc=='?')
            return helper(s, p, i-1, j-1, memo);
        else
        {
            if(helper(s, p, i, j-1, memo)||helper(s, p, i-1, j, memo))
            {
                memo[i][j]=1;
                return true;
            }
            memo[i][j]=-1;
            return false;
        }   
    }
}
