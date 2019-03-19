time: O（nlgn）
space: O(n)

public class Solution {
  public int[] mergeSort(int[] array) {
    // Write your solution here
    if (array == null || array.length == 0) {
      return array;
    }
    // 分配O(n)的空间作为中间结果保存空间
    int[] helper = new int[array.length];
    mergeSort(array, helper, 0, array.length-1);
    return array;
    
  }
  
  public void mergeSort(int[] a, int[] helper, int left, int right) {
    if (left >= right) {
      return;
    }
    int mid = left + (right-left)/2;
    mergeSort(a, helper, left, mid);
    mergeSort(a, helper, mid+1, right);
    merge(a, helper, left, mid, right);
  }
  
  private void merge(int[] a, int [] helper, int left, int mid, int right) {
    // copy array中对应的元素到helper中。
    for (int i = left; i <= right; i++) {
      helper[i] = a[i];
    }
    int leftIndex = left;
    int rightIndex = mid+1;
    while (leftIndex <= mid && rightIndex <= right) {
      if (helper[leftIndex] <= helper[rightIndex]) {
        a[left++] = helper[leftIndex++];
      } else {
        a[left++] = helper[rightIndex++];
      }
    }
    // 如果左半边还有剩下的，顺着copy.
    while (leftIndex <= mid) {
      a[left++] = helper[leftIndex++];
    }
    // 如果右半边还有剩下的，不用copy, 因为它们已经在原来的右半边，值大，不用动
  }
  
}
