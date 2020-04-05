package cn.wangjie.learn.thread;


/**
 * @param
 * @Author WangJie
 * @Description java中线程类型 分为用户线程和守护线程，
 * 当所有用户线程执行结束后，守护线程会立即结束，JVM退出
 * @Date 2020/4/5 18:21
 */
public class ThreadTypeDemo {

    public static void main(String[] args) {
        testDaemonThread();
    }

    public static void testUserThread(){
        System.out.println("parent thread begin ");
        ChildThread t1 = new ChildThread("thread1");
        ChildThread t2 = new ChildThread("thread2");
        t1.start();
        t2.start();
        System.out.println("parent thread over ");
    }

    /**
     * @Author WangJie
     * @Description 测试守护进程
     * 通过setDaemon(true)开启守护进程，必须在守护进程start前设置，否则报错
     * @param
     * @Date  2020/4/5 18:30
     */
    public static void testDaemonThread(){
        System.out.println("parent thread begin ");
        ChildThread t1 = new ChildThread("thread1");
        ChildThread t2 = new ChildThread("thread2");
        t1.setDaemon(true);
        t1.start();
        t2.setDaemon(true);
        t2.start();
        System.out.println("parent thread over ");
    }
}

class ChildThread extends Thread {
    private String name = null;

    public ChildThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(this.name + "--child thead begin");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println(this.name + "--child thead over");
    }
}


