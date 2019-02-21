题目要求是o(logn)的时间。 
有个test case: [1,2] 返回1  ->2
[1,10,2,3,4,5,6] 返回6   -> 6
    since its right neighbor is vacant and is considered negative infinite according to the description.

class Solution {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length-1;
        while (low < high) {
            int middle = low + (high-low)/2;
            if (nums[middle] < nums[middle+1]) {
                low = middle+1;
            } else {
                high = middle; // 如果是middle-1，会错过当前的最大值（有=的情况），此时nums[middle]>=nums[middle+1]
            }
        }
        return low;
    }
}
