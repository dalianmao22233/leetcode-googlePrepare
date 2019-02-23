// 蓄水池算法：http://blog.jobbole.com/42550/  讲的很清楚

对这个问题我们首先从最简单的例子出发：数据流只有一个数据。我们接收数据，发现数据流结束了，直接返回该数据，该数据返回的概率为1。
看来很简单，那么我们试试难一点的情况：假设数据流里有两个数据。

我们读到了第一个数据，这次我们不能直接返回该数据，因为数据流没有结束。我们继续读取第二个数据，发现数据流结束了。
因此我们只要保证以相同的概率返回第一个或者第二个数据就可以满足题目要求。
因此我们生成一个0到1的随机数R,如果R小于0.5我们就返回第一个数据，如果R大于0.5，返回第二个数据。

接着我们继续分析有三个数据的数据流的情况。为了方便，我们按顺序给流中的数据命名为1、2、3。
我们陆续收到了数据1、2和前面的例子一样，我们只能保存一个数据，所以必须淘汰1和2中的一个。
应该如何淘汰呢？不妨和上面例子一样，我们按照二分之一的概率淘汰一个，例如我们淘汰了2。继续读取流中的数据3，发现数据流结束了，
我们知道在长度为3的数据流中，如果返回数据3的概率为1/3,那么才有可能保证选择的正确性。
也就是说，目前我们手里有1,3两个数据，我们通过一次随机选择，以1/3的概率留下数据3，以2/3的概率留下数据1.那么数据1被最终留下的概率是多少呢？

数据1被留下：（1/2）*(2/3) = 1/3
数据2被留下概率：（1/2）*(2/3) = 1/3
数据3被留下概率：1/3
这个方法可以满足题目要求，所有数据被留下返回的概率一样！

因此，我们做一下推论：假设当前正要读取第n个数据，则我们以1/n的概率留下该数据，否则留下前n-1个数据中的一个。
以这种方法选择，所有数据流中数据被选择的概率一样。简短的证明：假设n-1时候成立，即前n-1个数据被返回的概率都是1/n-1,
当前正在读取第n个数据，以1/n的概率返回它。那么前n-1个数据中数据被返回的概率为：(1/(n-1))*((n-1)/n)= 1/n，假设成立。
 
 
 https://leetcode.com/problems/linked-list-random-node/discuss/85662/Java-Solution-with-cases-explain
简而言之：
1. 当stream只有1个点的时候，返回head.val, 概率1/1
2. 当有2个点的时候，1/2取，生成0-1之间的随机数， 如果大于1就用当前node值替换r, 否则不动r，r当前值仍然是head.val
3. 当有3个点的时候，r已经是第一个或者第二个点的值了。我们要决定替换掉r还是第三个点的值。1/3.  也就是说不碰r的概率是2/3. 所以生成一个0-2之间的
随机数， 如果为2，就替换r。  直到stream end位置。
 

//Each time call getRandom is indeed O(N) in average. To generate a new list with N random elements, it's O(N^2) in total.
class Solution {
    ListNode head;
    Random random;
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode h) {
        head = h;
        random = new Random();
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode c = head;
        int r = c.val;
        for (int i = 1; c.next != null; i++) {
            c = c.next;
            // 产生0-n之间的随机数
            if (random.nextInt(i+1) == i) {   // 如果随机生成的数和当前的i一样，就返回替换r为当前node的值。
                r = c.val;
            }
        }
        return r;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
