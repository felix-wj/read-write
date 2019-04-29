package cn.wangjie.learn;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.stream.Stream;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2019-04-01 18:01
 **/
public class ThreadlocalTest {
    @Test
    public void hashTest(){
        ThreadLocal<String> firstThreadLocal = new ThreadLocal<>();
        ThreadLocal<String> secondThreadLocal = new ThreadLocal<>();
        Class c = ThreadLocal.class;
        Field[] fields = c.getDeclaredFields();
        Stream.of(fields).forEach(field -> {
            //获取访问权限并输出
            int modifiers = field.getModifiers();
            System.out.print(Modifier.toString(modifiers) + " ");
            //输出变量的类型及变量名
            field.setAccessible(true);
            try {
                System.out.println(field.getType().getName()
                        + " " + field.getName()+":"+field.get(firstThreadLocal));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        });
    }

    @Test
    public void printHashNum(){
        int HASH_INCREMENT = 0x61c88647;
        System.out.println(HASH_INCREMENT);
        System.out.println(Integer.toBinaryString(HASH_INCREMENT));
    }

}
