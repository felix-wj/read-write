package cn.wangjie.learn.thread;

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
        volatileTest();
    }
    /**
     * @Author WangJie
     * @Description 无法保证写操作原子性
     * @param
     * @Date  2020/4/5 18:48
     */
    public static void volatileTest()  {
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

}
