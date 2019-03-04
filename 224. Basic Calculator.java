1. 用stack:

class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        int sign = 1;
        int number = 0;  // 相当于求一个中间量。
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = 10*number + (int) (c-'0'); 
                //可能是个多位数，先输进来存着。如果不是的话number=0，会被后面的操作更新的，莫担心
            } else if (c == '+') {
                res += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                res += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {   
                stack.push(res);
                stack.push(sign);
                sign = 1;
                res = 0;
            } else if (c == ')') {
                res += sign * number;
                number = 0;
                res *= stack.pop();  // 对应左括号，这里是sign
                res += stack.pop();  // 对应左括号，这里是res.
            }
            
        }
        if (number != 0) {
            res += sign * number;
        }
        return res;
    }
}


2. 我看有个recursion... 但是没用stack.
    
class Solution {
    int i = 0;
    public int calculate(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int current = 0;
        int result = 0;
        int sign = 1;
        while(i < s.length()){  // for 也行。。
            char c = s.charAt(i++);
            if(c >= '0' && c <= '9'){  // 0-9之间
                current = current * 10 + c - '0';
            }else if(c == '+'){
                result += current * sign;
                current = 0;
                sign = 1;
            }else if(c == '-'){
                result += current * sign;
                current = 0;
                sign = -1;
            }else if(c == '('){
                current = calculate(s);
            }else if(c == ')'){
                result += current * sign;
                return result;
            }
        }
        result += current * sign;
        return result;
    }
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
