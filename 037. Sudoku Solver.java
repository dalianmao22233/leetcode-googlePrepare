class Solution {
    // 可以参考：https://github.com/bephrem1/backtobackswe/blob/master/Dynamic%20Programming%2C%20Recursion%2C%20%26%20Backtracking/sudokuSolver.java， 解释很清楚
    
    // 时间复杂度： 9 ^ m (m represents the number of blanks to be filled in), 每个空格有9种选择
    // 这道题是np complete. 时间复杂度不好分析，我也不清楚
    
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        dfs(board);
    }
    public boolean dfs(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) { // 试验，从1-9都试一遍
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;       // 把c放到cell中
                            if (dfs(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';  // 原来就是空格，dfs失败之后要回复状态， 再去找新的唯一解。
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isValid(char[][] board, int m, int n, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[i][n] == c) {   // 此col上不能出现c
                return false;
            }
        }
        for (int j = 0; j < 9; j++) {
            if (board[m][j] == c) {   // 此row上不能出现c
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {   //此cube中不能出现c
            if (board[m/3*3 + i/3][n/3*3 + i%3] == c) {
                return false;
            }
        }
        return true;
    }
}
