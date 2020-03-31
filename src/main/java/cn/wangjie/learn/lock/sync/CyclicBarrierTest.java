package cn.wangjie.learn.lock.sync;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2020-03-23 22:22
 **/
public class CyclicBarrierTest {

    public static void main(String[] args) {
        int num = 8;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num,()->{
            System.out.println("来到栅栏处，当前线程是:"+Thread.currentThread().getName());
        });
        Random random = new Random();
        for (int i = 0; i <num ; i++) {
            Thread thread = new Thread(()->{
                try {
                    Thread.sleep(random.nextInt(4000));
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            });
            thread.setName("线程"+i);
            thread.start();
        }
        //再次通过栅栏
        for (int i = 0; i <num ; i++) {
            Thread thread = new Thread(()->{
                try {
                    Thread.sleep(random.nextInt(4000));
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            });
            thread.setName("线程"+i);
            thread.start();
        }
    }
}
