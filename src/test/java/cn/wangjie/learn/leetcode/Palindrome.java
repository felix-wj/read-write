package cn.wangjie.learn.leetcode;

import org.junit.Test;

/**
 * @program: learn
 * @description: 回文数字
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 121
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 * <p>
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * @author: WangJie
 * @create: 2019-07-11 13:52
 **/
public class Palindrome {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10 && x > 0) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        char[] number={'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        while (x > 0) {
            sb.append(number[x%10]);
            x /=10;
        }
        for (int i = 0;i<=sb.length()/2-1;i++){
            if (sb.charAt(i)!=sb.charAt(sb.length()-i-1)){
                return false;
            }
        }
        return true;
    }
    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10 && x > 0) {
            return true;
        }
        int d = 1;
        int m = x;
        while (m>=10){
            m /= 10;
            d *=10;
        }
        int n;
        while (x>0){
            m = x%10;
            n = x/d;
            if (m!=n){
                return false;
            }
            x = x%d/10;
            d/=100;
        }
        return true;
    }
    public boolean isPalindrome3(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10 && x > 0) {
            return true;
        }
        int n = 0;
        int m = x;
        while (m>0){
            n = n*10+m%10;
            m/=10;
        }
        return n ==x;
    }
    @Test
    public void test(){
        System.out.println(isPalindrome3(112211));
    }
}
