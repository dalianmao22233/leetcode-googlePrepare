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


2. 更简洁：但是我不懂 还是用第一种吧

public int searchInsert(int[] nums, int target) {
    int low = 0, high = nums.length;
    while(low < high) {
        int mid = low + (high - low) / 2;
        if(nums[mid] < target)
            low = mid + 1;
        else
            high = mid;
    }
    return low;
}  
    
    
3. recursive 做法：

public int searchInsert(int[] nums, int target) {
    return firstOccurrenceRecur(nums,target,0,nums.length-1);
}
public int firstOccurrenceRecur(int[] nums, int target, int low, int high) {
    if (low > high) { return low; }
    int mid = low + ( (high - low) >> 1 );
    if (nums[mid] < target) {
        return firstOccurrenceRecur(nums,target,mid + 1,high);
    } else {
        return firstOccurrenceRecur(nums,target,low,mid-1);
    }
}

如果有重复数字：就是改进了方法2， 加了一个dup的判断
public int searchInsert(int[] input, int target) {
    // Write your solution here

    int left = 0, right = input.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (target == input[mid]) {
            while (mid >= 0 && input[mid] == target) {
                mid--;
            }
            return mid + 1;
        } else if (target > input[mid]) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return left;
}
