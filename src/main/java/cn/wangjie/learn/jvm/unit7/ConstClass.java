package cn.wangjie.learn.jvm.unit7;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2019-11-03 14:23
 **/
public class ConstClass {
    static{
        System.out.println("ConstClass init");
    }

    public static final String HELLOWORLD = "hello world";
}
