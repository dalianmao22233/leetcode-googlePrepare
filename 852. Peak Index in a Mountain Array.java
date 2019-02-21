1. binary search: O(log n)
class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int start = 0;
        int end = A.length - 1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) {
                return mid;
            }
            else if(A[mid] > A[mid + 1]) {   // 在下坡路上
                end = mid;
            }
            else {   // 在上坡路上
                start = mid + 1;
            }
        }
        return -1;
    }
}

2. 线性搜：O(n)
class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int i = 0;
        while (A[i] < A[i+1]) i++;
        return i; 
    }
}
