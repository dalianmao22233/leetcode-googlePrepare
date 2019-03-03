class Solution {
    public int search(ArrayReader reader, int target) {
        //find higher bound
        int r = 1;
        while (reader.get(r) < target) r *= 2;   // 这步很重要。 size未知，用这个能往前进步
        //then you know lower bound
        int l = r/2;
        while (l <= r) {
            int m = l+(r-l)/2;
            if (reader.get(m) == target) return m;
            else if (reader.get(m) < target) l = m+1;
            else r = m-1;
        }
        return -1;
    }
}
