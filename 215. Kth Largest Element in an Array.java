1. 这是一道的 Kth Element 问题，不过这道题要找的是从后往前第 K 个元素，而不是从前往后。
为了能和传统的 Kth Element 问题一样求解，可以先执行 k = nums.length - k;。

Input: [3,2,1,5,6,4] and k = 2
Output: 5


快速排序的 partition() 方法，对于数组 nums 的 [l, h] 区间，会返回一个整数 k 使得 nums[l..k-1] 小于等于 nums[k]，
且 nums[k+1..h] 大于等于 nums[k]，此时 nums[k] 就是数组的第 k 大元素。
可以利用这个特性找出数组的 Kth Element，这种找 Kth Element 的算法称为快速选择算法。

时间复杂度 平均O(N)、最差O(n^2)，空间复杂度 O(1)
只有当允许修改数组元素时才可以使用


class Solution {
    //time: O(n) 平均， worst:O(n^2), space: O(1)
    // 只有当允许修改数组元素时才可以使用
    
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        k = nums.length-k;   // 这道题是从后往前第k个数，预处理k， 变成从前往后第“k”个数
        //这里不能是nums.length-k+1, 举例 [1], k=1, k = 1-1=0才是合理的
        
        int l = 0; 
        int r = nums.length-1;
        while (l < r) {
            int pivot = partition(nums, l, r);
            if (pivot == k) {
                break;
            } else if (pivot < k) {
                l = pivot+1;
            } else {
                r = pivot-1;
            }
        }
        return nums[k];
    }
    public int partition(int[] a, int l, int r) {
        int i = l, j = r+1;
        while (true) {
            while (a[++i] < a[l] && i < r);   // a[i] = a[l]， ++i之后就是a[l]和a[l+1]比较，不会漏掉
            // System.out.println(i);  打印看看过程就懂了
            while (a[--j] > a[l] && j > l);   // a[j] = a[r]， --l之后就是a[r]和a[l]比较，不会漏掉最后一个元素
            // System.out.println(j);
            // i的左边都比l小， j的右边都比l大
            if (i >= j) {
                break;
            }
            swap(a, i, j);
            
            // for (int m = 0; m < a.length; m++) {
            //     System.out.print(a[m]+",");
            // }
            // System.out.println(".");
        }
        swap(a, l , j);
        
        // for (int m = 0; m < a.length; m++) {
        //     System.out.print("final:" + a[m]+",");
        // }
        // System.out.println("   j = " + j);
        return j; // j 是挡板了
    }
    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}

2. 大顶堆：O(nlogk), space: O(k)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 这一步很关键！
        // 总体思想就是：举例一共5个数，求第二大的，那大顶堆一定是size=4=5-2+1，最后能peek到这个堆顶。+1的意思就是原来的kth最大的。 
        // 举例子就想明白了
        k = nums.length - k + 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); // 大顶堆
        for (int val : nums) {
            pq.add(val);
            if (pq.size() > k)  // 维护堆的大小为 K
                pq.poll();
        }
        return pq.peek();
    }
}

3. 小顶堆：O(nlogk), space: O(k)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 小顶堆
        for (int val : nums) {
            pq.add(val);
            if (pq.size() > k) {
                //具体过程画图理解，最后一步是4,5,6， 移除堆顶4，还剩5作为堆顶，就是结果。
                pq.poll();
            }
        } 
        return pq.peek();
    }
}

4. 海量数据
在这种场景下，单机通常不能存放下所有数据。

拆分，可以按照哈希取模方式拆分到多台机器上，并在每个机器上维护最大堆；
整合，将每台机器得到的最大堆合并成最终的最大堆。

5. 频率统计

a. hashmap 进行频率统计，然后使用快速选择或者堆的方式找出频率 TopK。在海量数据场景下，也是使用先拆分再整合的方式来解决空间问题。
b. trie, 在词汇对应节点保存出现的频率。它很好地适应海量数据场景，因为 Trie 树通常不高，需要的空间不会很大。

