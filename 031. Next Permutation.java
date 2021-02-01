class Solution {
    
    //1. dfs 找所有permutation  O(n!), 求min, 不满足constant space要求而且很慢
    //2. inplace做法： Time: O(n), space: O(1)
   
    
 https://www.cnblogs.com/grandyang/p/4428207.html
 通过观察原数组可以发现，如果从末尾往前看，数字逐渐变大，到了2时才减小的，然后再从后往前找第一个比2大的数字，是3，那么我们交换2和3，再把此时3后面的所有数字转置一下即可，步骤如下：

1　　2=　　7　　4　　3　　1

1　　2　　7　　4　　3=　　1

1　　3=　　7　　4　　2　　1

1　　3　　1　　2　　4　　7
        
    
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // 1. 找降序的转折点i. 从后往前找。
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--; //i--之后的num[i]是升序的第一个了。
        }
// 另一种写法：    
//         int i = nums.length-1;
         
//         while (i > 0 && nums[i-1] >= nums[i]) {
//                 i--;
//         }
         
//         i--;
//         System.out.println(i);
//      以上两种写法的区别，
//      第一种，i是指向倒数第二个数，根据逻辑，最后能正好指向第一个转折点。 第二种写法，i指向倒数第一个数，最后指向最大的那个数（升序的最后一个），所以要再i--,才能找到转折点。
     
        // 2. 从后往前找比num[i]大的数字，和num[i]交换。
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--; // j--之后的j， num[j]就是比nums[i]大了。
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
