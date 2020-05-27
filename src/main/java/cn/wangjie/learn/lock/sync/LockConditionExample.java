package cn.wangjie.learn.lock.sync;

import cn.wangjie.learn.lock.sync.model.Consumer;
import cn.wangjie.learn.lock.sync.model.IProduct;
import cn.wangjie.learn.lock.sync.model.Producer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionExample {
    static class Product implements IProduct{
        private int num;
        private int maxNum;
        private Lock lock = new ReentrantLock();
        private Condition produceCondition = lock.newCondition();
        private Condition consumeCondition = lock.newCondition();

        public Product(int maxNum) {
            this.maxNum = maxNum;
        }

        @Override
        public void increase() {
            try {
                lock.lock();
                while (num>=maxNum){
                    produceCondition.await();
                }
                num++;
                System.out.println("生产者生产，数量+1,总数量:" + num);
                consumeCondition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }finally {
                lock.unlock();
            }
        }

        @Override
        public void decrease() {
            try {
                lock.lock();
                while (num<=0){
                    consumeCondition.await();
                }
                num--;
                System.out.println("消费者消费，数量-1,总数量:" + num);
                produceCondition.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                lock.unlock();
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
