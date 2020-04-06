package cn.wangjie.learn.thread;

import java.util.concurrent.TimeUnit;

/**
 * @program: learn
 * @description: volatile使用
 * volatile只能保证可见性，保证读操作原子性，但不保证写操作的可见性。
 * @author: WangJie
 * @create: 2020-04-05 18:43
 **/
public class VolatileDemo {
    static volatile int count = 0;

    public static void main(String[] args) throws InterruptedException {
        //volatileTest();
        readTest();
    }

    /**
     * @param
     * @Author WangJie
     * @Description 无法保证写操作原子性
     * @Date 2020/4/5 18:48
     */
    public static void volatileTest() {
        for (int i = 0; i < 80000; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    count++;
                }
            });
            thread.start();
        }
        try {
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(count);
    }


    public static class Worker {
        private boolean stopThread = false;
        private WorkThread workThread;

        public Worker() {
            workThread = new WorkThread();
        }

        public void start() {
            stopThread = false;
            workThread.start();
        }

        public void stop() {
            stopThread = true;
        }

        public class WorkThread extends Thread {
            private long i = 0;

            @Override
            public void run() {
                super.run();

                System.out.println("start........");
                while (!stopThread) {
                    i++;
                }
                System.out.println("end........");
            }
        }
    }

    /**
     * @Author WangJie
     * @Description stopThread未用volatile修饰，线程无法停止
     * 使用volatile修饰stopThread后，保证了修改对其他线程的可见性
     * 但是，此处如果不使用volatile，而是在i++上面加一行输出代码System.out.println(i);可以发现最终也达到了停止线程的效果。
     * 这是因为println方法内使用了synchronized锁，JVM有一个锁优化原则：锁膨胀
     * 即如果一系列的连续操作都对同一个对象反复加锁和解锁，甚至加锁操作是出现在循环体中的，
     * 那即使没有线程竞争，频繁地进行互斥同步操作也会导致不必要的性能损耗。
     * 如果虚拟机探测到有这样一串零碎的操作都对同一个对象加锁，将会把加锁同步的范围扩展（膨胀）到整个操作序列的外部（由多次加锁编程只加锁一次）。
     * 所以代码变成了
     * synchronized(this){
     *                     while (!stopThread) {
     *                         System.out.println(i);
     *                         i++;
     *                     }
     *                 }
     * @param      
     * @Date  2020/4/6 20:46
     */
    public static void readTest() {
        Worker worker = new Worker();
        worker.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.stop();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



