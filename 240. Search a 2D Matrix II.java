//从右上角往下搜就是找更大的，往左搜就是找更小的，或者可以从左下角开始。
//时间复杂度：o(m+n) 空间复杂度：o(1)

这道题是从右上角开始，如果> taraget, 向左看， < target， 向下看

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int row = matrix.length;
        int column = matrix[0].length;
        int i = 0, j = column-1;
        while (i < row && j >= 0) {
            if (matrix[i][j] ==  target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;  // 从第一行分析，如果>target,应该往左找小的。
            } else {
                i++;
            }
        }
        
        return false;
    }
}


https://leetcode.com/problems/search-a-2d-matrix-ii/solution/ 还有其他解法，binary search, divide and conquer等
