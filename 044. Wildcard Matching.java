'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.

1. dp:
public boolean isMatch(String s, String p) {
    	if(s==null || p==null) return false;
    	int s_len = s.length();
    	int p_len = p.length();
    	
    	boolean[][] d = new boolean[s_len+1][p_len+1];
    	d[0][0] = true;
    	
    	for (int i = 0; i < p_len; i++) {
        if ( p.charAt(i) == '*') d[0][i+1] = d[0][i];
      }
    	
    	for (int i = 0; i < s_len; i++) {
        for (int j = 0; j < p_len; j++) {
          if(p.charAt(j) == '?') d[i+1][j+1] = d[i][j];
          if(p.charAt(j) == s.charAt(i)) d[i+1][j+1] = d[i][j];
          if(p.charAt(j) == '*') d[i+1][j+1] = d[i+1][j] || d[i][j+1];
        }
      }
    	
    	return d[s_len][p_len];
}
    
    
public boolean isMatch(String s, String p) {
      int m = s.length(), n = p.length();
      char[] sc = s.toCharArray();
      char[] pc = p.toCharArray();
      boolean[][] dp = new boolean[m + 1][n + 1];
      dp[0][0] = true;
      for(int j = 1; j < n + 1; j++){
        if(pc[j - 1] == '*') dp[0][j] = dp[0][j - 1]; 
      }   
      
      for(int i = 1; i < m + 1; i++){
        for(int j = 1; j < n + 1; j++){
          if(pc[j - 1] == sc[i - 1] || pc[j - 1] == '?'){
            dp[i][j] = dp[i - 1][j - 1];
          } else if(pc[j - 1] == '*'){
            dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
          } else {
            dp[i][j] = false;
          }
        }
      }
      return dp[m][n];
    }
    
    
2. dfs+memo
public boolean isMatch(String s, String p) {
        int[][] memo = new int[s.length()][p.length()];
        return helper(s, p, s.length()-1, p.length()-1, memo);     
    }
    
    public boolean helper(String s, String p, int i, int j, int[][] memo)
    {
        if(i<0&&j>=0)
        {
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
