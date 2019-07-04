package cn.wangjie.learn.leetcode;

import org.junit.Test;

/**
 * @program: learn
 * @description: 整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 *  示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 * 注意:
 * <p>
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 * @author: WangJie
 * @create: 2019-07-04 09:25
 **/
public class IntReverse {
    public int reverse(int x) {
        if (x < 10 && x > -10) {
            return x;
        }
        StringBuilder xStr = new StringBuilder(Integer.toString(x));
        if (x < 0) {
            xStr.deleteCharAt(0);
        }
        xStr = xStr.reverse();
        String s = xStr.toString();
        if (x > 0) {
            if (isOverflow(s, 1)) {
                return 0;
            } else {
                return Integer.parseInt(s);
            }
        } else {
            if (isOverflow(s, 0)) {
                return 0;
            } else {
                return Integer.parseInt("-" + s);
            }
        }
    }

    private boolean isOverflow(String s, int type) {
        if (s.length() < 10) {
            return false;
        }
        if (type == 1) {
            return s.compareTo("2147483647") > 0;
        } else {
            return s.compareTo("2147483648") > 0;
        }
    }

    public int reverse2(int x) {
        int max = Integer.MAX_VALUE / 10;
        int min = Integer.MIN_VALUE / 10;
        int t = 0;
        while (x != 0) {
            int n = x % 10;
            x = x / 10;
            if (t > max || (t == max && n > 7)) {
                return 0;
            }
            if (t < min || (t == min && n < -8)) {
                return 0;
            }
            t = t * 10 + n;
        }
        return t;
    }

    @Test
    public void test() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(reverse2(Integer.MIN_VALUE));
        System.out.println(reverse2(-244));

    }
}
