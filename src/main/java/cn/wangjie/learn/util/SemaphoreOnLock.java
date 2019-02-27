package cn.wangjie.learn.util;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: read-write
 * @description: 使用lock实现信号量
 * @author: WangJie
 * @create: 2019-02-27 16:08
 **/
public class SemaphoreOnLock {
    private final Lock lock = new ReentrantLock();
    private int permits ;
    private Condition permitAvailable = lock.newCondition();

    public SemaphoreOnLock(int permits) {
        this.permits = permits;
    }

    public void acquire() throws InterruptedException {
        try {
            lock.lock();
            while (permits<0){
                permitAvailable.await();
            }
            --permits;
        }finally {
            lock.unlock();
        }
    }

    public void release(){
        try {
            lock.lock();
            ++permits;
            permitAvailable.signal();
        }finally {
            lock.unlock();
        }
    }


}
