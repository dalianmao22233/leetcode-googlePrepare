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
