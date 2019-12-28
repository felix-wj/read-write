package cn.wangjie.learn;

import lombok.Data;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

/**
 * @program: learn
 * @description: 测试泛型类
 * @author: WangJie
 * @create: 2019-12-21 21:23
 **/
public class GenericTest {
    @Data
    class Person<T> {

    }

    /**
     * 演示泛型数组为什么是不安全的
     * 不能创建参数化类型数组
     */
    public static <T extends Comparable> T[] minmax(T... a) {
        Object[] mm = new Object[2] ;
        mm[0] = a[0];
        mm[1] = a[0];
        for (T t : a) {
            if (t.compareTo(mm[0])==1){
                mm[0]= t;
            }
            if (t.compareTo(mm[1])==-1){
                mm[1] = t;
            }
        }
        return (T[])mm;
    }

    public static <T extends Comparable> T[] minmax1(T... a) {
        T[] mm = (T[])Array.newInstance(a.getClass().getComponentType(),2);
        mm[0] = a[0];
        mm[1] = a[0];
        for (T t : a) {
            if (t.compareTo(mm[0])==1){
                mm[0]= t;
            }
            if (t.compareTo(mm[1])==-1){
                mm[1] = t;
            }
        }
        return mm;
    }
    public static <T extends Comparable> T[] minmax2(IntFunction<T[]> constr ,T... a) {
        T[] mm = constr.apply(2);
        mm[0] = a[0];
        mm[1] = a[0];
        for (T t : a) {
            if (t.compareTo(mm[0])==1){
                mm[0]= t;
            }
            if (t.compareTo(mm[1])==-1){
                mm[1] = t;
            }
        }
        //return (T[])mm; //会抛出异常java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.Comparable;
        return mm;
    }

    @Test
    public void test() {
        Person<String>[] ps = (Person<String>[]) new Person<?>[10];
        Object[] os = ps;
        os[0] = "";
        System.out.println(os[0]);
    }

    @Test
    public void test2() {
        Integer[] ss = GenericTest.minmax2(Integer[]::new,1,2,3);
        System.out.println(ss[0]+" "+ss[1]);
        List<Integer> a = new ArrayList<>();
        a.add(0);
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        List<Integer> a1 = a.subList(1, 3);
        a1.set(0,6);
        a1.add(3);
        System.out.println(a);
    }
}
