package cn.wangjie.learn.proxy;

import java.util.function.Supplier;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2019-10-31 10:43
 **/
public class LogHandlerLombda {

    public static  <T> T log(Supplier<T> f ){
        System.out.println("执行前输出");
        T t = f.get();
        System.out.println("执行后输出");
        return t;
    }
}
