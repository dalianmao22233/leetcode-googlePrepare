my solution:

class Solution {
    public int[] sortArrayByParity(int[] A) {
        if (A == null || A.length == 0) {
            return new int[0];
        }
        int k = 0;  // 顺次往后排！这样能保证前面的都是even.
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                swap(A, k, i);
                k++;
            }
        }
        return A;
    }
    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
