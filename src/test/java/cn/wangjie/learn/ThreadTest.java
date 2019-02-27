package cn.wangjie.learn;

import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: read-write
 * @description:
 * @author: WangJie
 * @create: 2019-02-18 10:38
 **/
public class ThreadTest {
    @Test
    public void test(){
        ExecutorService executor =Executors.newFixedThreadPool(10);
        Executors.unconfigurableExecutorService(executor);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable");
            }
        };
    }
}
