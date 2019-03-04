1. 其中大堆保存右半段较大的数字，小堆保存左半段较小的数组。
这样整个数组就被中间分为两段了，由于堆的保存方式是由大到小，
我们希望大堆里面的数据是从小到大，这样取第一个来计算中位数方便。
最后就是小顶堆里都是数组里小的，大顶堆里都是大的，排了两端的序，然后取出来两个的顶点，这样就能计算中位数了。
举例子：[1,2,100,3,2,99]

执行之后：even能保证两个heap进来的数一样多。
small: [1,2,2], top=2;  这里都是数组中较小的， top就是最大的
large: [100,3,99], top = 3; 这里都是数组中较大的， top就是最小的
median = 2.5


class MedianFinder {

    /** initialize your data structure here. */
    public MedianFinder() {
        
    }
    
    private PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());  // 大顶堆，默认顺序是小顶堆
    private PriorityQueue<Integer> large = new PriorityQueue<>();  // 小顶堆
    private boolean even = true;

    public double findMedian() {
        if (even)
            return (small.peek() + large.peek()) / 2.0;
        else
            return small.peek();
    }

    
    public void addNum(int num) {
        if (even) {
            large.offer(num);
            small.offer(large.poll());   // 大顶堆 加进来 小顶堆的顶，也就是最小的
        } else {
            small.offer(num);
            large.offer(small.poll());
        }
        even = !even;
    }

}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
