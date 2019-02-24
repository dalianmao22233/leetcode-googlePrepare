就是两个数相乘，输出结果，只不过数字很大很大，都是用 String 存储的。也就是传说中的大数相乘。

1. 普通乘法过程
时间复杂度：O（m * n）。m，n 是两个字符串的长度。

空间复杂度：O（1）。

个位乘个位，得出一个数，然后个位乘十位，全部乘完以后，就再用十位乘以各个位。然后百位乘以各个位，最后将每次得出的数相加。
十位的结果要补 1 个 0 ，百位的结果要补两个 0 。相加的话我们可以直接用之前的大数相加。



class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }  
        String ans = "0";
        int index = 0; //记录当前是哪一位，便于后边补 0 
        for (int i = num2.length() - 1; i >= 0; i--) {
            int carry = 0; //保存进位
            String ans_part = ""; //直接用字符串保存每位乘出来的数
            int m = num2.charAt(i) - '0';
            //乘上每一位
            for (int j = num1.length() - 1; j >= 0; j--) {
                int n = num1.charAt(j) - '0';
                int mul = m * n + carry; 
                ans_part = mul % 10 + "" + ans_part;
                carry = mul / 10;
            }
            if (carry > 0) {
                ans_part = carry + "" + ans_part;
            }
            //补 0 
            for (int k = 0; k < index; k++) {
                ans_part = ans_part + "0";
            }
            index++;
            //和之前的结果相加
            ans = sumString(ans, ans_part);
        }
        return ans;
    }
    //大数相加
    private String sumString(String num1, String num2) {
        int carry = 0;
        int num1_index = num1.length() - 1;
        int num2_index = num2.length() - 1;
        String ans = "";
        while (num1_index >= 0 || num2_index >= 0) {
            int n1 = num1_index >= 0 ? num1.charAt(num1_index) - '0' : 0;
            int n2 = num2_index >= 0 ? num2.charAt(num2_index) - '0' : 0;
            int sum = n1 + n2 + carry;
            carry = sum / 10;
            ans = sum % 10 + "" + ans;
            num1_index--;
            num2_index--;
        }
        if (carry > 0) {
            ans = carry + "" + ans;
        }
        return ans;
    }
}






2. https://leetcode.com/problems/multiply-strings/discuss/17605/Easiest-JAVA-Solution-with-Graph-Explanation
https://leetcode.windliang.cc/leetCode-43-Multiply-Strings.html 有解释
时间复杂度：O（m * n）。m，n 是两个字符串的长度。

空间复杂度：O（m + n）。m，n 是两个字符串的长度。

1. compute products from each pair of digits from num1 and num2. 
2. carry each element over. 
3. output the solution.

Things to note:

The product of two numbers cannot exceed the sum of the two lengths. (e.g. 99 * 99 cannot be five digit)

int d1 = num1.charAt(i) - '0';
int d2 = num2.charAt(j) - '0';
products[i + j + 1] += d1 * d2;


public class Solution {
    public String multiply(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length();
        int[] products = new int[n1 + n2];
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int d1 = num1.charAt(i) - '0';
                int d2 = num2.charAt(j) - '0';
                products[i + j + 1] += d1 * d2;
            }
        }
        int carry = 0;
        for (int i = products.length - 1; i >= 0; i--) {
            int tmp = (products[i] + carry) % 10;
            carry = (products[i] + carry) / 10;
            products[i] = tmp;
        }
        StringBuilder sb = new StringBuilder();
        for (int num : products) sb.append(num);
        while (sb.length() != 0 && sb.charAt(0) == '0') sb.deleteCharAt(0);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}



或者：
public String multiply(String num1, String num2) {
    if (num1.equals("0") || num2.equals("0")) {
        return "0";
    }
    int n1 = num1.length();
    int n2 = num2.length();
    int[] pos = new int[n1 + n2]; //保存最后的结果
    for (int i = n1 - 1; i >= 0; i--) {
        for (int j = n2 - 1; j >= 0; j--) {
            //相乘的结果
            int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            //加上 pos[i+j+1] 之前已经累加的结果
            int sum = mul + pos[i + j + 1];
            //更新 pos[i + j]
            pos[i + j] += sum / 10;
            //更新 pos[i + j + 1]
            pos[i + j + 1] = sum % 10;
        }
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < pos.length; i++) {
        //判断最高位是不是 0 
        if (i == 0 && pos[i] == 0) {
            continue;
        }
        sb.append(pos[i]);
    }
    return sb.toString();
}
Copy
