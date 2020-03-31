package cn.wangjie.learn.lock.sync;

import cn.wangjie.learn.util.TimeIntervalCountUtil;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.function.Supplier;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2020-03-25 21:11
 **/
public class SemaphoreTest {
    public static void main(String[] args) {
        int N = 8;
        Semaphore semaphore = new Semaphore(4);
        Random random = new Random();

        for (int i = 0; i < N; i++) {
            Thread t = new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "开始工作");
                    Thread.sleep(random.nextInt(10) * 1000);
                    semaphore.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            t.setName("线程" + i);
            t.start();
        }


    }
}
