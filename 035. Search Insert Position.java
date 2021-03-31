acwing: 模板
class Solution {
    public int searchInsert(int[] nums, int target) {
        int l = 0; 
        int r = nums.length; //注意这里不要-1， 因为可能最后插入到最后一个元素后面，越界了
        
        while (l < r) {
            int mid = l+r >> 1;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid+1;
            }
        }
        return l;
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
