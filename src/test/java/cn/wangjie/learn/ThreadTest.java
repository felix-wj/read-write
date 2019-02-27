package cn.wangjie.learn;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @program: read-write
 * @description:
 * @author: WangJie
 * @create: 2019-02-18 10:38
 **/
public class ThreadTest {
    @Test
    public void test() throws ExecutionException, InterruptedException {
        ExecutorService executor =Executors.newFixedThreadPool(10);
        Executors.unconfigurableExecutorService(executor);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable");
            }
        };
        Callable<Integer> integerCallable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        };
        FutureTask futureTask = new FutureTask(integerCallable);
        futureTask.run();
        System.out.println(futureTask.get());
    }
}
