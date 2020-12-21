Principle:

(Sign before '+'/'-') = (This context sign);
(Sign after '+'/'-') = (This context sign) * (1 or -1);
Algorithm:

Start from +1 sign and scan s from left to right;
if c == digit: This number = Last digit * 10 + This digit;
if c == '+': Add num to result before this sign; This sign = Last context sign * 1; clear num;
if c == '-': Add num to result before this sign; This sign = Last context sign * -1; clear num;
if c == '(': Push this context sign to stack;
if c == ')': Pop this context and we come back to last context;
Add the last num. This is because we only add number after '+' / '-'.
   来自第一条评论： https://leetcode.com/problems/basic-calculator/discuss/62361/Iterative-Java-solution-with-stack

public int calculate(String s) {
    if(s == null) return 0;
        
    int result = 0;
    int sign = 1;
    int num = 0;
            
    Stack<Integer> stack = new Stack<Integer>();
    stack.push(sign);
            
    for(int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
                
        if(c >= '0' && c <= '9') {
            num = num * 10 + (c - '0');
            //如果有多位的数字，比如123， 那么一位一位放。
        } else if(c == '+' || c == '-') {
            result += sign * num;
            sign = stack.peek() * (c == '+' ? 1: -1); 
            num = 0;
                    
        } else if(c == '(') {
            stack.push(sign);
                    
        } else if(c == ')') {
            stack.pop();
        }
    }
            
    result += sign * num;
    return result;
}



 

3. 和变种III思路一致：
在做了Basic Calculator III之后，再反过头来看这道题，发现递归处理括号的方法在这道题也同样适用，
我们用一个变量cnt，遇到左括号自增1，遇到右括号自减1，
当cnt为0的时候，说明括号正好完全匹配，
这个trick在验证括号是否valid的时候经常使用到。
然后我们就是根据左右括号的位置提取出中间的子字符串调用递归函数，返回值赋给num

class Solution {
public:
    int calculate(string s) {
        int res = 0, num = 0, sign = 1, n = s.size();
        for (int i = 0; i < n; ++i) {
            char c = s[i];
            if (c >= '0' && c <= '9') {
                num = 10 * num + (c - '0');
            } else if (c == '(') {
                int j = i, cnt = 0;
                for (; i < n; ++i) {
                    if (s[i] == '(') ++cnt;
                    if (s[i] == ')') --cnt;
                    if (cnt == 0) break;
                }
                num = calculate(s.substr(j + 1, i - j - 1));
            }
            if (c == '+' || c == '-' || i == n - 1) {
                res += sign * num;
                num = 0;
                sign = (c == '+') ? 1 : -1;
             } 
        }
        return res;
    }
};
