
// We are using 7 and -8 because the 32 bit INT_MAX and INT_MIN last digit is 7 & -8 respectively; 
// so if the rev is already equal to INT_MAX/10 and pop is >= 7 (or less than -8) then it'll be overflow.
//这是一道简单题，但是依然有两个知识点需要掌握。

//首先，要记住INT_MIN=-2^31，对它去绝对值的话是会溢出整形的。所以对一个任意整形取绝对值的时候一定要考虑它是否可能是INT_MIN。

//其次，如果快速判断一个数字在进位的过程中是否溢出？比如说如何判断x*10 > INT_MAX？其实交换一下位置，用if (x > INT_MAX/10)就巧妙地规避溢出报错的问题
//看做一个stack, 123， 先Pop出1，->2，->3

class Solution {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}

// wrong, 极限情况 Integer.MAX_VALUE 超出32位1534236469， return 0， temp=rev⋅10+pop can cause overflow.
class Solution {
    public int reverse(int x) {
        int y = 0;
        int z = 0;
        while (x/10 != 0) {
            y = x%10; //注意顺序，先做y, 否则x会先被处理
            x = x/10;
            z = z*10 + y*10;
        }
        z = z + x%10;
        return z;
    }
}

