my solution:

class Solution {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length-1;
        while (left < right-1) {   // 来offer的模板，left right相邻的时候停下来，这就是target的大概位置了，
        // 1. left, right 之间是target, 
        // 2. right 右边是target
        // 3. left 左边是target
        
            int mid = left + (right-left)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        // System.out.println(left + "," + right);
        
        if (target == nums[left]) {
            return left; // [1,3], target=1
        }
        if (target == nums[right]) {
            return right; // [1,3], target=3
        }
        
        if (target > nums[left] && target < nums[right]) {
            return right;
        }
        if (target > nums[left] && target > nums[right]) {
            return right+1;
        }
        return left;  // 都不满足就放左边呗。。 就是target比nums[left]小
    }
}
