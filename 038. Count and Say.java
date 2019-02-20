exmaple:
 1.     1   1
 2.     11  1个1  解释1这个字符串
 3.     21  2个1  解释2这个字符串
 4.     1211  1个2 1个1 解释3这个字符串
 5.     111221 。。。
 6.     312211
 7.     13112221
 8.     1113213211
 9.     31131211131221
 10.   13211311123113112211
  
  
  
1. iterative method:

public class Solution {
    public String countAndSay(int n) {
        String s = "1";
        for(int i = 1; i < n; i++){
            s = countIdx(s);
        }
        return s;
    }
    
    public String countIdx(String s){
        StringBuilder sb = new StringBuilder();
        char c = s.charAt(0);
        int count = 1;
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) == c){
                count++;
            }
            else
            {
                sb.append(count);
                sb.append(c);
                c = s.charAt(i);
                count = 1;
            }
        }
        sb.append(count);
        sb.append(c);
        return sb.toString();
    }
}


2. recursive:

class Solution {
    public String countAndSay(int n) {
        if(n==1)
            return "1";

        StringBuilder sb=new StringBuilder();

        //找到n-1的结果
        String str=countAndSay(n-1);
       
        //对n-1的结果进行表示
        char c='0';
        int count=0;
        for (int i=0;i<str.length();i++){
            c=str.charAt(i);
            count=1;
            while ((i+1)<str.length()&&str.charAt(i+1)==c){
                count++;
                i++;
            }
            sb.append(count+""+c);
        }
        return sb.toString();
    }
}

