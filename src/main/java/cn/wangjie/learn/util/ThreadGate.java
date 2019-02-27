package cn.wangjie.learn.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: read-write
 *
 * @description: 可重复关闭的阀门
 * @author: WangJie
 * @create: 2019-02-27 11:43
 **/

public class ThreadGate {
    private boolean isOpen;
    private int generation;


    public ThreadGate() {
        isOpen = false;
        generation = 0;
    }

    public synchronized void close() {
        isOpen = false;
    }

    public synchronized void open() {
        isOpen = true;
        ++generation;
        notifyAll();
    }

    public synchronized void await() throws InterruptedException {
        int arrivalGeneration = generation;
        while (!isOpen && arrivalGeneration == generation) {
            wait();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ThreadGate threadGate = new ThreadGate();

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("线程1等待阀门开启");
                    try {
                        threadGate.await();
                    } catch (InterruptedException e) {
                        System.out.println("线程中断");
                        Thread.currentThread().interrupt();
                        break;
                    }
                    System.out.println("线程1阀门开启，可以执行");
                    System.out.println("线程1执行完毕，关闭阀门");
                    threadGate.close();
                }
            }
        };
        executorService.execute(runnable1);
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("线程2等待阀门开启");
                    try {
                        threadGate.await();
                    } catch (InterruptedException e) {
                        System.out.println("线程中断");
                        Thread.currentThread().interrupt();
                        break;
                    }
                    System.out.println("线程2阀门开启，可以执行");
                    System.out.println("线程2执行完毕，关闭阀门");
                    threadGate.close();
                }
            }
        };
        executorService.execute(runnable2);

        Thread.sleep(2000L);
        System.out.println("开启阀门");
        threadGate.open();
        Thread.sleep(2000L);
        System.out.println("开启阀门");
        threadGate.open();
        Thread.sleep(2000L);
        executorService.shutdownNow();

    }

}
