http://www.cnblogs.com/grandyang/p/4465932.html  看这个帖子解释
1. recursive做法：

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


