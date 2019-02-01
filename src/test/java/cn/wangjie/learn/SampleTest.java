package cn.wangjie.learn;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @program: read-write
 * @description:
 * @author: WangJie
 * @create: 2019-01-10 21:45
 **/
public class SampleTest {

    @Test
    public void  testEnum(){
        String str = "abc";
        StringBuffer stringBuffer = new StringBuffer(str);
        stringBuffer.reverse();
        System.out.println(stringBuffer);
        Suit.values();
    }
    enum Suit{
        a,
        b,
        c,
    }
    private volatile int  count = 0;

    @Test
    public void volatileTest() throws InterruptedException {
        for (int i = 0; i<20000;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    count++;
                }
            });
            thread.start();
        }
        Thread.sleep(4000L);
        System.out.println(count);
    }
    @Test
    public void volatileTest2() throws InterruptedException {
        final CountDownLatch done = new CountDownLatch(80000);
        for (int i = 0; i<80000;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                        count++;
                        done.countDown();
                }
            });
            thread.start();
        }
        done.await();
        System.out.println(count);

    }


}
