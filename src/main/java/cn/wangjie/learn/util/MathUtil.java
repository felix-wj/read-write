package cn.wangjie.learn.util;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2020-01-02 17:37
 **/
public class MathUtil {

    /**
     * @param str 形如 (T&F)&(F|T)
     * @Author WangJie
     * @Description 布尔运算
     * @Date 2020/1/2 17:39
     */
    public static boolean booleanCalculate(String str) {
        Deque<Character> s1 = new ArrayDeque<>();
        Stack<Character> s2 = new Stack<>();
        str = str.trim().replaceAll(" ", "");
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            if (aChar == 'F' || aChar == 'T') {
                s1.push(aChar);
            } else if (aChar == '(') {
                s2.push(aChar);
            } else if (aChar == '&' || aChar == '|') {
                if (!s2.isEmpty()&&(s2.peek()== '&' || s2.peek() == '|')) {
                    s1.push(s2.pop());
                }
                s2.push(aChar);
            } else if (aChar == ')') {
                while (s2.peek() != '(') {
                    s1.push(s2.pop());
                }
                s2.pop();
            }
        }
        while (!s2.empty()) {
            s1.push(s2.pop());
        }
        while (!s1.isEmpty()) {
            Character c = s1.pollLast();
            if (c == '&' || c == '|') {
                boolean a = s2.pop() == 'T';
                boolean b = s2.pop() == 'T';
                if (c == '&') {
                    s2.push(a & b == true ? 'T' : 'F');
                }else {
                    s2.push(a | b == true ? 'T' : 'F');
                }
            }else {
                s2.push(c);
            }
        }
        return s2.pop()=='T';
    }

    public static void main(String[] args) {
        System.out.println(booleanCalculate("(T&F)&(F|T)"));
        System.out.println(booleanCalculate("(F&F)&(F|T)"));
        System.out.println(booleanCalculate("(T&F)|(F|T)"));
        System.out.println(booleanCalculate("(T&F)&(F&T)"));
    }
}
