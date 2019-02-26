class Solution {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        
        for (Character c : s.toCharArray()) {
            if ("{[(".contains(String.valueOf(c))) { // string.valueOf  来转化char sequence到string,不然报错
                stack.push(c);
            } else if (!stack.isEmpty() && isValidCombination(stack.peek(), c)){
                stack.pop();
            } else {
                return false;  // remember return false, if "]", then stack is empty, finally return true
            }
        }
        return stack.isEmpty();
    }
    private boolean isValidCombination(char c1, char c2) {
        return (c1 == '(' && c2 == ')') || (c1 == '[' && c2 == ']') || (c1 == '{' && c2 == '}');
    }
}

// good!
class Solution {
    public boolean isValid(String s) {
       		char[] stack = new char[s.length()];
        int head = 0;
        
        for (char c: s.toCharArray()) {
            switch (c) {
                case '(': stack[head++] = ')'; break;
                case '{': stack[head++] = '}'; break;
                case '[': stack[head++] = ']'; break;
                default: if (head == 0 || stack[--head] != c) return false; break;
            }
        }
        return head == 0;
	}
    
   
}
