package cn.wangjie.learn;

import lombok.Data;
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

    @Test
    public void test4Thread() throws InterruptedException {
        @Data
        class Point{
            private char c ;
        }
        Point p = new Point();
        p.setC('A');
        Semaphore semaphore = new Semaphore(1);
        Thread t1 = new Thread(()->{
            try{
                while (true){
                    semaphore.acquire();
                    while (p.getC()!='A'){
                        semaphore.release();
                        semaphore.acquire();
                    }
                    System.out.println(p.getC());
                    p.setC('B');
                    semaphore.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        t1.start();
        Thread t2 = new Thread(()->{
            try{
                while (true){
                    semaphore.acquire();
                    while (p.getC()!='B'){
                        semaphore.release();
                        semaphore.acquire();
                    }
                    System.out.println(p.getC());
                    p.setC('C');
                    semaphore.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        t2.start();
        Thread t3 = new Thread(()->{
            try{
                while (true){
                    semaphore.acquire();
                    while (p.getC()!='C'){
                        semaphore.release();
                        semaphore.acquire();
                    }
                    System.out.println(p.getC());
                    p.setC('D');
                    semaphore.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        t3.start();
        Thread t4 = new Thread(()->{
            try{
                while (true){
                    semaphore.acquire();
                    while (p.getC()!='D'){
                        semaphore.release();
                        semaphore.acquire();
                    }
                    System.out.println(p.getC());
                    p.setC('A');
                    semaphore.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        t4.start();

        Thread.sleep(20*1000);
    }
}
