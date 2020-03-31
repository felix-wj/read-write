package cn.wangjie.learn.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: learn
 * @description: 位运算
 * @author: WangJie
 * @create: 2020-01-30 21:30
 **/
public class BitOperation {
    public static void main(String[] args) {
        int i = 1;
        System.out.println(i^1^2);
    }

    /**
     * 异或运算
     *性质： 相同的数异或运算后结果为0
     *      任何数和0异或运算不改变结果
     */
    @Test
    public void test1(){
        System.out.println(1^1);
        System.out.println(0^2);
        System.out.println(0^0);
    }

}
