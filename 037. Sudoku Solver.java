class Solution {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) return;
        dfs(board, 0, 0);
        
    }
    public boolean dfs(char[][] board, int i, int j) {
        if (i == 9) return true;
        if (j == 9) return dfs(board, i+1, 0);
        if (board[i][j] != '.') return dfs(board, i, j+1);
        
        
        for (char c = '1'; c <= '9'; c++) {
            if (isValid(board, i, j, c)) {
                board[i][j] = c;
                if (dfs(board, i, j+1)) return true;
                board[i][j] = '.';
            }
        }
        return false;

    }
    public boolean isValid(char[][] board, int m, int n, char c) {
        // 行不能有c
        for (int i = 0; i < 9; i++) {
            // 注意加上i!=m, 非m行的如果有c，就invalid.
            if (i != m && board[i][n] == c) {
                return false;
            }
        } 
        // 列不能有c
        for (int j = 0;j < 9; j++) {
            if (j != n && board[m][j] == c) {
                return false;
            }
        }
        // cube不能有c
        int start_row=m/3*3;
        int start_col=n/3*3;
        
        // 先用start_row , start_col找到对应的cube，然后在cube里traverse
        // ie: m=4, n=4, 
        // for p in (4-6)
        //     for q in (4-6)
        // can traverse 9 cells.
        for (int p=start_row; p<start_row+3; p++)
         for (int q=start_col; q<start_col+3; q++)
         {
             if ((p!=m||q!=n) && board[p][q]==c)
                 return false;
         }
        return true;
    }
}
