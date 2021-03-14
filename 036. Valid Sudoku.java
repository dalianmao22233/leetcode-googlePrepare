acwing: 超级好理解！ 相当于判断row/col/cube是否有重复数字，用一个boolean数组就行了

class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[] st = new boolean[9];
        // row
        for (int i = 0; i < 9; i++) {
            Arrays.fill(st, false);
            //行固定，扫列
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int t = board[i][j] - '1';
                    if (st[t]) {
                        return false;
                    }
                    st[t] = true;
                }
            }
        }
        // col
        for (int i = 0; i < 9; i++) {
            Arrays.fill(st, false);

            //列固定，扫行
            for (int j = 0; j < 9; j++) {
                if (board[j][i] != '.') {
                    int t = board[j][i] - '1';
                    if (st[t]) {
                        return false;
                    }
                    st[t] = true;
                }
            }
        }
        // cube
        for (int i = 0; i < 9; i = i+3) {

            
            for (int j = 0; j < 9; j = j+3) {
                Arrays.fill(st, false);
                //这种好记，不用/3之类的，每三个跳一次，去里面循环
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        if (board[i+x][j+y] != '.') {
                            int t = board[i+x][j+y] - '1';
                            if (st[t]) {
                                return false;
                            }
                            st[t] = true;
                        }
                    }
                }
                
            }
        }
        return true;
    }
}
