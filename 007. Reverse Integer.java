
// We are using 7 and -8 because the 32 bit INT_MAX and INT_MIN last digit is 7 & -8 respectively; 
// so if the rev is already equal to INT_MAX/10 and pop is . greater than 7 (or less than -8) then it'll be overflow.

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

