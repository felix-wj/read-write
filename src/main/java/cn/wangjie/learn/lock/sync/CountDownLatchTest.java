package cn.wangjie.learn.lock.sync;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2020-03-22 18:29
 **/
public class CountDownLatchTest {
    public static void runTimeCount(int nThread, final Runnable runnable) {
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(nThread);
        for (int i = 0; i < nThread; i++) {
            Thread t = new Thread(() -> {
                try {
                    start.await();
                    try {
                        runnable.run();
                    } finally {
                        end.countDown();
                    }
                } catch (
                        InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            t.start();
        }
        long startTime = System.nanoTime();
        start.countDown();
        try {
            end.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        long endTime = System.nanoTime();
        System.out.println("用时：" + (endTime - startTime)/1000000000D + "s");
    }

    public static void main(String[] args) {
        Random random = new Random();
        CountDownLatchTest.runTimeCount(8, () -> {
            try {
                Thread.sleep(random.nextInt(4000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("over---");
        });
    }

}
