1. 这个很好懂，参考：https://leetcode.com/problems/valid-sudoku/solution/， 看图就行了
time:O(1) space:O(1)

判断标准是看各行各列是否有重复数字，以及每个小的 3x3 的小方阵里面是否有重复数字，如果都无重复，
则当前矩阵是数独矩阵，但不代表待数独矩阵有解，只是单纯的判断当前未填完的矩阵是否是数独矩阵。
根据数独矩阵的定义，在遍历每个数字的时候，就看看包含当前位置的行和列以及 3x3 小方阵中是否已经出现该数字，
这里需要三个 boolean 型矩阵，大小跟原数组相同，分别记录各行，各列，各小方阵是否出现某个数字，其中行和列标志下标很好对应，就是小方阵的下标需要稍稍转换一下

class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] cell = new boolean[9][9];
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                
                int c = board[i][j] - '1';
                if (row[i][c] || col[c][j] || cell[i/3*3 + j/3][c]) {
                    return false;
                }
                row[i][c] = true;
                col[c][j] = true;
                cell[i/3*3 + j/3][c] = true;
            }
        }
        return true;
    }
}


2. 
Collect the set of things we see, encoded as strings. For example:

'4' in row 7 is encoded as "(4)7".
'4' in column 7 is encoded as "7(4)".
'4' in the top-right block is encoded as "0(4)2".
Scream false if we ever fail to add something because it was already added (i.e., seen before).
这种做饭就是把括号换成了"in row" "in column"... ， 在set中看是否有相同的string. 

public boolean isValidSudoku(char[][] board) {
    Set seen = new HashSet();
    for (int i=0; i<9; ++i) {
        for (int j=0; j<9; ++j) {
            char number = board[i][j];
            if (number != '.')
                if (!seen.add(number + " in row " + i) ||
                    !seen.add(number + " in column " + j) ||
                    !seen.add(number + " in block " + i/3 + "-" + j/3))
                    return false;
        }
    }
    return true;
}
