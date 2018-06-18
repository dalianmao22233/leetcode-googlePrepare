class Solution {
    public int numJewelsInStones(String J, String S) {
        if (J == null || J.length() == 0 || S == null || S.length() == 0) {
            return -1;
        }
        int count = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < J.length(); i++) {
            map.put(J.charAt(i), i);
        }
        for (int j = 0; j < S.length(); j++) {
            if (map.containsKey(S.charAt(j))) {
                count ++;
            }
        }
        // for (int i = 0; i < J.length(); i++) {
        //     for (int j = 0; j < S.length(); j++) {
        //         if (J.charAt(i) == S.charAt(j)) {
        //             count++;
        //         }
        //     }
        // }
        return count;
    }
}