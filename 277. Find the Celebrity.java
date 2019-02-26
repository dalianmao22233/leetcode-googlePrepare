1. 先跳出来candidate. 如果candidate know i, 那么就交换i和candidate. 意思就是可能i不知道candidate. 
2. 验证candidate是否是真的。
a. i == candidate. 此时就是自己知道自己
b. i != candidate, 此时就是candidate 不知道i.  i一定知道candidate.
符合以上2个条件才是真的。

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int candidate = 0;
        for(int i = 1; i < n; i++){
            if(knows(candidate, i))
                candidate = i;
        }
        for(int i = 0; i < n; i++){
            if(i != candidate && (knows(candidate, i) || !knows(i, candidate))) return -1;
        }
        return candidate;
    }
}
