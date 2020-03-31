package cn.wangjie.learn.util;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2020-01-02 17:37
 * 后缀表达式的计算机求值：
 * 与前缀表达式类似，只是顺序是从左至右：
 * 从左至右扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，用运算符对它们做相应的计算（次顶元素 op 栈顶元素），并将结果入栈；重复上述过程直到表达式最右端，最后运算得出的值即为表达式的结果。
 * 例如后缀表达式“3 4 + 5 × 6 -”：
 * (1) 从左至右扫描，将3和4压入堆栈；
 * (2) 遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素，注意与前缀表达式做比较），计算出3+4的值，得7，再将7入栈；
 * (3) 将5入栈；
 * (4) 接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
 * (5) 将6入栈；
 * (6) 最后是-运算符，计算出35-6的值，即29，由此得出最终结果。
 *
 * 将中缀表达式转换为后缀表达式：
 * 与转换为前缀表达式相似，遵循以下步骤：
 * (1) 初始化两个栈：运算符栈S1和储存中间结果的栈S2；
 * (2) 从左至右扫描中缀表达式；
 * (3) 遇到操作数时，将其压入S2；
 * (4) 遇到运算符时，比较其与S1栈顶运算符的优先级：
 * (4-1) 如果S1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
 * (4-2) 否则，若优先级比栈顶运算符的高，也将运算符压入S1（注意转换为前缀表达式时是优先级较高或相同，而这里则不包括相同的情况）；
 * (4-3) 否则，将S1栈顶的运算符弹出并压入到S2中，再次转到(4-1)与S1中新的栈顶运算符相比较；
 * (5) 遇到括号时：
 * (5-1) 如果是左括号“(”，则直接压入S1；
 * (5-2) 如果是右括号“)”，则依次弹出S1栈顶的运算符，并压入S2，直到遇到左括号为止，此时将这一对括号丢弃；
 * (6) 重复步骤(2)至(5)，直到表达式的最右边；
 * (7) 将S1中剩余的运算符依次弹出并压入S2；
 * (8) 依次弹出S2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式（转换为前缀表达式时不用逆序）。
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
        System.out.println(booleanCalculate("F"));
    }
}
