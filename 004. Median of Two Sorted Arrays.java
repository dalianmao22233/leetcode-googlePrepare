大致思路：
1. trick：找2个数组的中间数，考虑了奇数偶数情况，最终用：
找第 (m+n+1) / 2 个，和 (m+n+2) / 2 个，然后求其平均值即可，这对奇偶数均适用。
if m+n 为奇数的话，那么其实 (m+n+1) / 2 和 (m+n+2) / 2 的值相等，相当于两个相同的数字相加再除以2，还是其本身

2. 怎么找到kth元素？
用i, j 分别记录两个数组的起点； k=1的时候比较起点就完事了
用二分法：对K二分；在两个数组中分别找第 k/2 个数字。
首先需要check 是否数组中存在k/2 th数字。 如果存在就取出来，否则赋值为max-int
比较这两个数组的k/2 th 数字 mid1, mid2的大小。
mid1 < mid2, 意味着数组1中前k/2个数里肯定没有我要找的第K个数。将这一部分淘汰， 数组1的起始位置i向后移动k/2个位置， k=k-k/2, 调用递归函数。
否则， 对数组2做相同的事。	

public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length, left = (m + n + 1) / 2, right = (m + n + 2) / 2;
        return (findKth(nums1, nums2, left) + findKth(nums1, nums2, right)) / 2.0;
    }
    int findKth(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        if (m == 0) return nums2[k - 1];
        if (n == 0) return nums1[k - 1];
        if (k == 1) return Math.min(nums1[0], nums2[0]);
	
        int i = Math.min(m, k / 2), j = Math.min(n, k / 2);
        if (nums1[i - 1] > nums2[j - 1]) {
            return findKth(nums1, Arrays.copyOfRange(nums2, j, n), k - j); // 舍弃nums2的前一半
        } else {
            return findKth(Arrays.copyOfRange(nums1, i, m), nums2, k - i);  // 舍弃nums1的前一半
        }
    }
}



http://www.cnblogs.com/grandyang/p/4465932.html  看这个帖子解释
1. recursive做法：
https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2499/Share-my-simple-O(log(m+n))-solution-for-your-reference 
这个有更好的写法，我不懂以下这种。

public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
	int m = nums1.length, n = nums2.length, left = (m + n + 1) / 2, right = (m + n + 2) / 2;
	return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
}

private static int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
	if (i >= nums1.length) return nums2[j + k - 1];
	if (j >= nums2.length) return nums1[i + k - 1];
	if (k == 1) return Math.min(nums1[i], nums2[j]);
	int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
	int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
	if (midVal1 < midVal2) {
		return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
	} else {
		return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
	}
}
	
2. 就普通做法。
https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2471/very-concise-ologminmn-iterative-solution-with-detailed-explanation
有解释
double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
    int N1 = nums1.size();
    int N2 = nums2.size();
    if (N1 < N2) return findMedianSortedArrays(nums2, nums1);	// Make sure A2 is the shorter one.

    int lo = 0, hi = N2 * 2;
    while (lo <= hi) {
	int mid2 = (lo + hi) / 2;   // Try Cut 2 
	int mid1 = N1 + N2 - mid2;  // Calculate Cut 1 accordingly

	double L1 = (mid1 == 0) ? INT_MIN : nums1[(mid1-1)/2];	// Get L1, R1, L2, R2 respectively
	double L2 = (mid2 == 0) ? INT_MIN : nums2[(mid2-1)/2];
	double R1 = (mid1 == N1 * 2) ? INT_MAX : nums1[(mid1)/2];
	double R2 = (mid2 == N2 * 2) ? INT_MAX : nums2[(mid2)/2];

	if (L1 > R2) lo = mid2 + 1;		// A1's lower half is too big; need to move C1 left (C2 right)
	else if (L2 > R1) hi = mid2 - 1;	// A2's lower half too big; need to move C2 left.
	else return (max(L1,L2) + min(R1, R2)) / 2;	// Otherwise, that's the right cut.
    }
    return -1;
} 


