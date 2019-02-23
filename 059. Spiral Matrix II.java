和spiral matrix I 解法完全相同


public class Solution {
    public int[][] generateMatrix(int n) {
        // Declaration
        int[][] matrix = new int[n][n];
        
        // Edge Case
        if (n == 0) {
            return matrix;
        }
        
        // Normal Case
        int top = 0;
        int bottom = n-1;
        int left = 0;
        int right = n-1;
        int num = 1; //change
        
        while (top <= bottom && left <= right) {   // 可以换为total
            for (int i = left; i <= right; i ++) {
                matrix[top][i] = num ++; //change
            }
            top ++;
            
            for (int i = top; i <= bottom; i ++) {
                matrix[i][right] = num ++; //change
            }
            right --;
            
            for (int i = right; i >= left; i --) {
                //if (top <= bottom)    //spiral I 需要
                    matrix[bottom][i] = num ++; //change
            }
            bottom --;
            
            for (int i = bottom; i >= top; i --) {
                //if (left <= right)    //spiral I 需要
                    matrix[i][left] = num ++; //change
            }
            left ++;
        }
        
        return matrix;
    }
}
