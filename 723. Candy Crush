class Solution {
    public int[][] candyCrush(int[][] board) {
        int N = board.length, M = board[0].length;
        boolean found = true;
        while (found) {
            found = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int val = Math.abs(board[i][j]);
                    if (val == 0) continue;
                    if (j < M - 2 && Math.abs(board[i][j + 1]) == val && Math.abs(board[i][j + 2]) == val) {
                        found = true;
                        int ind = j;
                        while (ind < M && Math.abs(board[i][ind]) == val) board[i][ind++] = -val;
                    }
                    if (i < N - 2 && Math.abs(board[i + 1][j]) == val && Math.abs(board[i + 2][j]) == val) {
                        found = true;
                        int ind = i;
                        while (ind < N && Math.abs(board[ind][j]) == val) board[ind++][j] = -val;           
                    }
                }
            }
            if (found) { // move positive values to the bottom, then set the rest to 0
                for (int j = 0; j < M; j++) {
                    int storeInd = N - 1;
                    for (int i = N - 1; i >= 0; i--) {
                        if (board[i][j] > 0) {
                            board[storeInd--][j] = board[i][j];
                        }
                    }
                    for (int k = storeInd; k >= 0; k--) board[k][j] = 0;
                }
            }
        }
        return board;
    }
}
