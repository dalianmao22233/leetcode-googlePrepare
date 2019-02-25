1. 答案中的：
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> heap = new PriorityQueue<String>(
                (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                w2.compareTo(w1) : count.get(w1) - count.get(w2) );  //这是小顶堆

        for (String word: count.keySet()) {
            heap.offer(word);
            if (heap.size() > k) heap.poll(); //截胡了。
        }

        List<String> ans = new ArrayList();
        while (!heap.isEmpty()) ans.add(heap.poll());
        Collections.reverse(ans);
        return ans;
    }
}


2. my solution:
pq: Time： O(NlogN), space: O(N)

class Solution {
    class Pair {
        String word;
        int count;
        public Pair(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<String>();
        if (words == null || words.length == 0 || k <= 0) {
            return res;
        }
        HashMap<String, Integer> map = new HashMap<>();
        
        for (String word: words) {
            map.put(word, map.getOrDefault(word, 0) +1);
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                if (p1.count == p2.count) {
                    return p1.word.compareTo(p2.word);  // word 之间比较用string内置的compareTo
                } 
                return p2.count-p1.count;
            }
        });
        for (Map.Entry<String, Integer> entry: map.entrySet()) {   /// 注意写法。
            pq.offer(new Pair(entry.getKey(), entry.getValue()));
            // 这里没办法维持K的大小的heap,因为我做的是大顶堆，如果中途因为满了K而poll，会poll出最大的元素，这是错的。
        }
        
        while (k > 0) {
            res.add(pq.poll().word);
            k--;
        }
        
        return res;
        
    }
}
