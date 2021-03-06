package cn.wangjie.learn.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: learn
 * @description: Z字形变换
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 * 示例 1:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 *
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 * @author: WangJie
 * @create: 2019-07-03 11:15
 **/
public class ZConvert {
    public String convert(String s, int numRows) {
        if (s == null || s.equals("")) {
            return s;
        }
        Map<Integer, List<Character>> lines = new HashMap<>();
        List<Character> line;
        int n;
        int t = numRows > 2 ? 2 * numRows - 2 : numRows;
        for (int i = 0; i < s.length(); i++) {
            n = i % t;
            if (n < numRows) {
                line = lines.computeIfAbsent(n + 1, k -> new ArrayList<>());
                line.add(s.charAt(i));
            } else {
                line = lines.computeIfAbsent(2 * numRows - n - 1, k -> new ArrayList<>());
                line.add(s.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 1; j <= numRows; j++) {
            line = lines.get(j);
            if (line != null)
                for (Character c : line) {
                    sb.append(c);
                }

        }
        return sb.toString();
    }

    @Test
    public void test() {
        String s = "LEETCODEISHIRING";
        System.out.println(convert3(s, 4).equals(convert2(s, 4)));
    }

    public String convert2(String s, int numRows) {
        if (s == null || s.equals("")) {
            return s;
        }
        char[] chars = s.toCharArray();
        int t = numRows > 2 ? 2 * numRows - 2 : numRows;
        char[][] lineChars = new char[numRows][];
        int m = s.length() / t;
        if (s.length() % t > 0) {
            m++;
        }
        lineChars[0] = new char[m];
        if (0 != numRows - 1) {
            lineChars[numRows - 1] = new char[m];
        }
        for (int rowNum = 1; rowNum < numRows - 1; rowNum++) {
            lineChars[rowNum] = new char[2 * m];
        }
        int[] lineNum = new int[numRows];
        int n;
        int line;
        for (int i = 0; i < s.length(); i++) {
            n = i % t;
            if (n < numRows) {
                lineChars[n][lineNum[n]] = chars[i];
                lineNum[n]++;
            } else {
                line = 2 * numRows - n - 2;
                lineChars[line][lineNum[line]] = chars[i];
                lineNum[line]++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < numRows; j++) {
            for (int h = 0; h < lineNum[j]; h++) {
                sb.append(lineChars[j][h]);
            }
        }
        return sb.toString();
    }

    public String convert3(String s, int numRows) {
        if (s == null || s.equals("")||numRows == 1) {
            return s;
        }
        int t = numRows > 2 ? 2 * numRows - 2 : numRows;
        int m = s.length() / t;
        StringBuilder sb = new StringBuilder();
        int index;
        for (int i = 0; i < numRows; i++) {
            if (i == 0 || i == numRows - 1) {
                for (int j = 0; j < m; j++) {
                    sb.append(s.charAt(j * t + i));
                }
                index = m * t + i;
                if (index < s.length()) {
                    sb.append(s.charAt(index));
                }
            } else {
                for (int j = 0; j < m; j++) {
                    index = j * t;
                    sb.append(s.charAt(index + i));
                    sb.append(s.charAt(index + 2 * numRows - i - 2));
                }
                index = m * t + i;
                if (index < s.length()) {
                    sb.append(s.charAt(index));
                }
                index = m * t + 2 * numRows - i - 2;
                if (index < s.length()) {
                    sb.append(s.charAt(index));
                }

            }
        }
        return sb.toString();
    }
}
