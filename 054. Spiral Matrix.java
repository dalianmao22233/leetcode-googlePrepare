1. 就按照顺序打印，注意边界， 先定好4条边的界限，然后循环打印，更新4个边界
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
    
        if (matrix == null || matrix.length == 0) {
            return result;
        }
        int rows = matrix.length; //rows
        int columns = matrix[0].length; //columns
        int total = rows * columns;
        int left = 0, right = matrix[0].length-1; //last column
        int top = 0, bottom = matrix.length - 1; //last row
        while (total > 0) {
            if (total > 0) {
                for (int i = left; i <= right; i++) {
                    result.add(matrix[top][i]);
                    total--;
                }
            }
            top++;
            if (total > 0) {
                for (int i = top; i <= bottom; i++) {
                    result.add(matrix[i][right]);
                    total--;
                }
            }
            right--;
            if (total > 0) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                    total--;
                }
            }
            bottom--;
            if (total > 0) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                    total--;
                }
            }
            left++;
        }
        return result;
    }
}


2. recursive--来offer
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        spiralOrder(matrix, m, n, 0, res);
        return res;  
    }
    
     public void spiralOrder(int[][] matrix, int m, int n, int offset, List<Integer> res) {
        if (m <= 0 || n <= 0) {
            return;
        }
         
        for (int i = 0; i < n - 1; i++) {
            res.add(matrix[offset][offset + i]);
        }
         
        for (int i = 0; i < m - 1; i++) {
            res.add(matrix[offset + i][offset + n - 1]);
        }
         
        for (int i = 0; i < (m == 1 ? 1 : n - 1); i++) {
            res.add(matrix[offset + m - 1][offset + n - 1 - i]);
        }
         
        for (int i = 0; i < (n == 1 && m != 1 ? 1 : m - 1); i++) {
            
            res.add(matrix[offset + m - 1 - i][offset]);
        }
         
        spiralOrder(matrix, m - 2, n - 2, offset + 1, res);
    }
}