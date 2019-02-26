1. My solution:
小顶堆，size=k,最后一个一个拿出来，O(nlogk)

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int len = nums.length;
        if (len == 1) {
            res.add(nums[0]);
            return res;
        }
        if (k > len) {
            k = k % len;  
        }

        HashMap<Integer, Integer> map  = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }
        
        // 此处可以直接用integer作为pq类型，因为只需要根据count排序数组元素。
        // 这种更好。省空间。PriorityQueue<Integer> heap =
            new PriorityQueue<Integer>((n1, n2) -> map.get(n1) - map.get(n2));
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] i1, int[] i2) {
                return i1[1]-i2[1];
            }
        });
        // 小顶堆， size=k
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            pq.offer(new int[]{entry.getKey(), entry.getValue()});
            if (pq.size() > k) {
                pq.poll();
            }
        }
        while (!pq.isEmpty()) {
            res.add(pq.poll()[0]);
        }
        Collections.reverse(res);  // 这步不写也没事。
        return res;
    }
}

2. bucket sort, 这种要掌握！Build a array of list to be buckets with length 1 to sort.

public List<Integer> topKFrequent(int[] nums, int k) {

	List<Integer>[] bucket = new List[nums.length + 1];
	Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

	for (int n : nums) {
		frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
	}

	for (int key : frequencyMap.keySet()) {
		int frequency = frequencyMap.get(key);
		if (bucket[frequency] == null) {
			bucket[frequency] = new ArrayList<>();
		}
		bucket[frequency].add(key);
	}

	List<Integer> res = new ArrayList<>();

	for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
		if (bucket[pos] != null) {
			res.addAll(bucket[pos]);
		}
	}
	return res;
}


3. treemap


// use treeMap. Use freqncy as the key so we can get all freqencies in order
public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }
        
        TreeMap<Integer, List<Integer>> freqMap = new TreeMap<>();
        for(int num : map.keySet()){
           int freq = map.get(num);
           if(!freqMap.containsKey(freq)){
               freqMap.put(freq, new LinkedList<>());
           }
           freqMap.get(freq).add(num);
        }
        
        List<Integer> res = new ArrayList<>();
        while(res.size()<k){
            Map.Entry<Integer, List<Integer>> entry = freqMap.pollLastEntry();
            res.addAll(entry.getValue());
        }
        return res;
    }
}
