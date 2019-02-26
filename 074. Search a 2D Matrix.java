1. 因为这道题是每一行都比上一行最后一个元素大，所以可以展开成1维数组。 直接binary search.   O(logM + logN) 

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        
        int left = 0, right = m*n-1;
        
        while (left <= right) {
            int mid = left + (right-left)/2;
            int mid_val = matrix[mid/n][mid%n];
            
            if (mid_val == target) {
                return true;
            } else if (mid_val < target) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return false;
    }
}

2. search a 2d matrix II 做法： 从右上角看。 the worst case is O(m + n)
public boolean searchMatrix(int[][] matrix, int target) {
    int i = 0, j = matrix[0].length - 1;
    while (i < matrix.length && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }

    return false;
}
