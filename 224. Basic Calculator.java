1. 用stack:

class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        int sign = 1;
        int number = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = 10*number + (int) (c-'0');
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
                res *= stack.pop();
                res += stack.pop();
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
