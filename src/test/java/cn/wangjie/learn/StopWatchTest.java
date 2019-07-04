package cn.wangjie.learn;

import org.junit.Test;
import org.springframework.util.StopWatch;

/**
 * @program: learn
 * @description: spring 工具 StopWatch
 * @author: WangJie
 * @create: 2019-07-04 14:03
 **/
public class StopWatchTest {

    @Test
    public void test() throws InterruptedException {
        StopWatch sw = new StopWatch("test");

        sw.start("起床");
        Thread.sleep(1000);
        sw.stop();

        sw.start("洗漱");
        Thread.sleep(2000);
        sw.stop();

        sw.start("锁门");
        Thread.sleep(500);
        sw.stop();

        System.out.println(sw.prettyPrint());
        System.out.println("---");
        System.out.println(sw.getTotalTimeMillis());
        System.out.println("---");
        System.out.println(sw.getLastTaskName());
        System.out.println("---");
        System.out.println(sw.getLastTaskInfo());
        System.out.println("---");
        System.out.println(sw.getTaskCount());
    }
}
