package cn.wangjie.learn;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @program: read-write
 * @description:
 * @author: WangJie
 * @create: 2019-01-10 21:45
 **/
public class SampleTest {

    @Test
    public void testEnum() {
        String str = "abc";
        StringBuffer stringBuffer = new StringBuffer(str);
        stringBuffer.reverse();
        System.out.println(stringBuffer);
        Suit.values();
    }

    enum Suit {
        a,
        b,
        c,
    }

    private volatile int count = 0;

    @Test
    public void volatileTest() throws InterruptedException {
        for (int i = 0; i < 20000; i++) {
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
        for (int i = 0; i < 80000; i++) {
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

    @Test
    public void shift() {
        int i = 0;
        while (true) {
            if ((i >>> 16) > 0) {
                System.out.println(i);
                break;
            }
            i++;
        }
        System.out.println(65535 >>> 16);
        ArrayList a = new ArrayList<Integer>(2);
        a.add(1);
        a.add(2);
        a.add(3);
    }

    /**
     * 查找出中轴（默认是最低位low）的在numbers数组排序后所在位置
     *
     * @param numbers 带查找数组
     * @param low     开始位置
     * @param high    结束位置
     * @return 中轴所在位置
     */
    public static int getMiddle(int[] numbers, int low, int high) {
        int temp = numbers[low]; //数组的第一个作为中轴
        while (low < high) {
            while (low < high && numbers[high] >= temp) {
                high--;
            }
            numbers[low] = numbers[high];//比中轴小的记录移到低端
            while (low < high && numbers[low] < temp) {
                low++;
            }
            numbers[high] = numbers[low]; //比中轴大的记录移到高端
        }
        numbers[low] = temp; //中轴记录到尾
        return low; // 返回中轴的位置
    }

}
