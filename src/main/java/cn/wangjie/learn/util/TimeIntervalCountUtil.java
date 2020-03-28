package cn.wangjie.learn.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2020-03-25 21:17
 **/
public class TimeIntervalCountUtil {

    public static void count(Supplier supplier){
        long start = System.nanoTime();
        supplier.get();
        long end = System.nanoTime();
        System.out.println("用时"+(end-start)/1000000000D+"秒");
    }
}
