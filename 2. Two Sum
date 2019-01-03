1. brute force:
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2]; // make sure initialization style.
        if (nums == null || nums.length == 0) {
            return result;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {  // make sure i and j are not duplicate
                if (nums[i] == target - nums[j]) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}


2. 1 for loop with 1 hashmap:
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        if (nums == null || nums.length == 0) {
            return result;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(target-nums[i])) {
                map.put(nums[i], i);
            } else {
                result[0] = map.get(target-nums[i]);
                result[1] = i;
                return result;
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}

follow up. 如果不考虑返回的index,返回为哪两个数之和==target,可以用2个pointer的方法得到结果, nums sorted....
public static int[] twoSum(int[] nums, int target) {
		int[] res = new int[2];
		if (nums == null || nums.length == 0) {
			return res;
		}
		HashMap<Integer, Integer> map = new HashMap<>(); // key: nums[i], value: index i
		int low = 0;
		int high = nums.length-1;

		while (low < high) {
			if (nums[low] + nums[high] == target) {
				res[0] = nums[low]; 
				res[1] = nums[high];
				return res;
			} else if (nums[low] + nums[high] < target) {
				low++;
			} else {
				high--;
			}
		}
		return res;
	}

follow up2: 找出有多少Pair满足x+y > target.
public static int findPairs(int arr[], int x) {

		int l = 0, r = arr.length-1;
		int result = 0;

		while (l < r) {

			// If current left and current
			// right have sum smaller than x,
			// the all elements from l+1 to r
			// form a pair with current l.
			if (arr[l] + arr[r] > x) {
				result += (r - l);
				l++;
			} else {
				// Move to smaller value
				r--;
			}
		}

		return result;
	}
