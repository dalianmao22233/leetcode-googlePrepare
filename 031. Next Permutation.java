class Solution {
    
    //1. dfs 找所有permutation  O(n!), 求min, 不满足constant space要求而且很慢
    //2. inplace做法： Time: O(n), space: O(1)
    主要就是要先找到尾部的最长递减序列的index first second，这样就可以很方便推导出下一个序列。
    
    在当前序列中，从尾端往前寻找两个相邻元素，前一个记为first，后一个记为second，并且满足first 小于 second。
    然后再从尾端寻找另一个元素number，如果满足first 小于number，
    即将第first个元素与number元素对调，并将second元素之后（包括second）的所有元素颠倒排序，即求出下一个序列
    
    example:
    6，3，4，9，8，7，1
    此时 first ＝ 4，second = 9
    从尾巴到前找到第一个大于first的数字，就是7
    交换4和7，即上面的swap函数，此时序列变成6，3，7，9，8，4，1
    再将second＝9以及以后的序列重新排序，让其从小到大排序，使得整体最小，即reverse一下（因为此时肯定是递减序列）
    得到最终的结果：6，3，7，1，4，8，9
    
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);  //reverse the number after the number we have found
    }

    private void reverse(int[] nums, int start) { 
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
