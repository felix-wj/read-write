package cn.wangjie.learn.leetcode;

import org.junit.Test;


/**
 * @program: learn
 * @description: 正则匹配
 * @author: WangJie
 * @create: 2019-07-15 12:00
 **/

public class MatchStr {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean firstMatch = (!s.isEmpty()) && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        if (p.length() > 1 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p));
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }

    public boolean isMatch2(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;
        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                boolean firstMatch = (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));
                if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || (firstMatch && dp[i + 1][j]);
                } else {
                    dp[i][j] = firstMatch && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }


    @Test
    public void test() {
        System.out.println(isMatch2("aab", "c*a*b*c*d*"));
        System.out.println(isMatch2("aab", "c*a*b"));
        System.out.println(isMatch2("aab", "c*a*bb*"));
        System.out.println(isMatch2("aab", "c*a*b**"));
        System.out.println(isMatch2("aab", ".*a"));
        System.out.println(isMatch2("aab", "aa.*"));
        System.out.println(isMatch2("ab", ".*c"));
        System.out.println(isMatch2("aaa", "a*a"));
    }
}
