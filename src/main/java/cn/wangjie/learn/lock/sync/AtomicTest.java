package cn.wangjie.learn.lock.sync;

import cn.wangjie.learn.lock.sync.model.Consumer;
import cn.wangjie.learn.lock.sync.model.IProduct;
import cn.wangjie.learn.lock.sync.model.Producer;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AtomicTest {

    static class Product implements IProduct {
        private AtomicInteger num;
        private volatile int maxNum;

        public Product(int maxNum) {
            num = new AtomicInteger(0);
            this.maxNum = maxNum;
        }

        public void increase() {
            while (true) {
                int curNum = num.get();
                if (curNum >= maxNum) {
                    continue;
                }
                int updateNum = curNum + 1;
                if (num.compareAndSet(curNum, updateNum)) {
                    System.out.println("生产者生产，数量+1,总数量:" + updateNum);
                    break;
                }
            }

        }

        public void decrease() {
            while (true) {
                int curNum = num.get();
                if (curNum <= 0) {
                    continue;
                }
                int updateNum = curNum - 1;
                if (num.compareAndSet(curNum, updateNum)) {
                    System.out.println("消费者消费，数量-1,总数量:" + updateNum);
                    break;
                }
            }

        }
    }


    public static void main(String[] args) {
        Product product = new Product(10);
        Producer producer = new Producer(product);
        producer.start();
        Producer producer2 = new Producer(product);
        producer2.start();
        Consumer consumer = new Consumer(product);
        consumer.start();
    }
}

