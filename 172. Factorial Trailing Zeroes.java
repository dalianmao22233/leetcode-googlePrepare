是让求一个数的阶乘末尾0的个数，也就是要找乘数中10的个数，而10可分解为2和5，
而我们可知2的数量又远大于5的数量，那么此题即便为找出5的个数。仍需注意的一点就是，像25,125，这样的不只含有一个5的数字需要考虑进去。

10 is the product of 2 and 5. In n!, we need to know how many 2 and 5, 
and the number of zeros is the minimum of the number of 2 and the number of 5.

Since multiple of 2 is more than multiple of 5, the number of zeros is dominant by the number of 5.

Here we expand

  2147483647!
=2 * 3 * ...* 5 ... *10 ... 15* ... * 25 ... * 50 ... * 125 ... * 250...
=2 * 3 * ...* 5 ... * (5^1*2)...(5^1*3)...*(5^2*1)...*(5^2*2)...*(5^3*1)...*(5^3*2)... (Equation 1)
We just count the number of 5 in Equation 1.

Multiple of 5 provides one 5, multiple of 25 provides two 5 and so on.

Note the duplication: multiple of 25 is also multiple of 5, so multiple of 25 only provides one extra 5.

Here is the basic solution:

return n/5 + n/25 + n/125 + n/625 + n/3125+...;
You can easily rewrite it to a loop.

//
1.Time complexity is log(n), space complexity is O(1).

public int trailingZeroes(int n) {
    int countOf5 = 0;
    long divider = 5;
    while (n / divider > 0) {
        countOf5 += (n / divider);
        divider *= 5;
     }
     return countOf5;
}


2.
Recursively applying this logic gives a call stack depth of Log5(n). 
Time complexity would be O(Log5(n)).

int trailingZeroes(int n) {
  return n >= 5 ? n / 5 + trailingZeroes(n / 5) : 0;
}
