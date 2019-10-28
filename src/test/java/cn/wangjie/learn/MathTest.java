package cn.wangjie.learn;

import org.junit.Test;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2019-08-08 11:15
 **/
public class MathTest {
    public static void main(String[] args) {

        String[] a = new String[3];

        List<List<String>> result = new ArrayList<>();
        combinationSelect(new String[] {
                "1", "2", "3", "4", "5"
        },0,a,0,result);
        for (List<String> list : result){
            System.out.println();
            for (String str : list){
                System.out.print(str+" ");
            }
        }
    }

    /**
     * 排列选择（从列表中选择n个排列）
     * @param dataList 待选列表
     * @param n 选择个数
     */
    public static void arrangementSelect(String[] dataList, int n) {
        System.out.println(String.format("A(%d, %d) = %d", dataList.length, n, arrangement(dataList.length, n)));
        arrangementSelect(dataList, new String[n], 0);
    }

    /**
     * 排列选择
     * @param dataList 待选列表
     * @param resultList 前面（resultIndex-1）个的排列结果
     * @param resultIndex 选择索引，从0开始
     */
    private static void arrangementSelect(String[] dataList, String[] resultList, int resultIndex) {
        int resultLen = resultList.length;
        if (resultIndex >= resultLen) { // 全部选择完时，输出排列结果
            System.out.println();
            return;
        }

        // 递归选择下一个
        for (int i = 0; i < dataList.length; i++) {
            // 判断待选项是否存在于排列结果中
            boolean exists = false;
            for (int j = 0; j < resultIndex; j++) {
                if (dataList[i].equals(resultList[j])) {
                    exists = true;
                    break;
                }
            }
            if (!exists) { // 排列结果不存在该项，才可选择
                resultList[resultIndex] = dataList[i];
                arrangementSelect(dataList, resultList, resultIndex + 1);
            }
        }
    }

    /**
     * 组合选择（从列表中选择n个组合）
     * @param dataList 待选列表
     * @param n 选择个数
     */
    public static <T> void combinationSelect(T[] dataList,T[] resultList  ,int n) {
        System.out.println(String.format("C(%d, %d) = %d", dataList.length, n, combination(dataList.length, n)));
        List<List<T>> lists = new ArrayList<>();
        combinationSelect(dataList, 0, resultList, 0,lists);


    }

    /**
     * 组合选择
     * @param dataList 待选列表
     * @param dataIndex 待选开始索引
     * @param resultList 前面（resultIndex-1）个的组合结果
     * @param resultIndex 选择索引，从0开始
     */
    private static <T> void combinationSelect(T[] dataList, int dataIndex, T[] resultList, int resultIndex,List<List<T>> lists) {
        int resultLen = resultList.length;
        int resultCount = resultIndex + 1;
        if (resultCount > resultLen) { // 全部选择完时，输出组合结果
            System.out.println(Arrays.asList(resultList));
            lists.add(asList(resultList));
            return;
        }

        // 递归选择下一个
        for (int i = dataIndex; i < dataList.length + resultCount - resultLen; i++) {
            resultList[resultIndex] = dataList[i];
            combinationSelect(dataList, i + 1, resultList, resultIndex + 1,lists);
        }
    }

    /**
     * 计算阶乘数，即n! = n * (n-1) * ... * 2 * 1
     * @param n
     * @return
     */
    public static long factorial(int n) {
        return (n > 1) ? n * factorial(n - 1) : 1;
    }

    /**
     * 计算排列数，即A(n, m) = n!/(n-m)!
     * @param n
     * @param m
     * @return
     */
    public static long arrangement(int n, int m) {
        return (n >= m) ? factorial(n) / factorial(n - m) : 0;
    }

    /**
     * 计算组合数，即C(n, m) = n!/((n-m)! * m!)
     * @param n
     * @param m
     * @return
     */
    public static long combination(int n, int m) {
        return (n >= m) ? factorial(n) / factorial(n - m) / factorial(m) : 0;
    }
    public static <T> List<T> asList(T... a) {
        List<T> ls = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (a[i] != null) {
                ls.add(a[i]);
            }
        }
        return ls;
    }

    @Test
    public void precentTest(){
        double rate = 0.26;
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(1);//设置保留小数位
        nf.setRoundingMode(RoundingMode.HALF_UP); //设置舍入模式
        String percent = nf.format(rate);
        if (percent.matches("\\.0%")){
            percent = percent.replace(".0%","%");
        }
        System.out.println(percent);
    }
}
