package cn.wangjie.learn.lock.sync;

import lombok.SneakyThrows;

/**
 * @program: learn
 * @description: Object 类的wait()方法和notify(),notifyAll()方法
 * @author: WangJie
 * @create: 2020-04-12 18:12
 **/
public class WaitAndNotify {

    static class Product {
        private int num = 0;

        private int maxNum;

        public Product(int maxNum) {
            this.maxNum = maxNum;
        }

        public synchronized void  increase(){
            while (num==maxNum){
                try {
                    System.out.println("已达最大数量，等待消费者消费");
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            num++;
            System.out.println("生产者生产，数量+1");
            notifyAll();
        }

        public synchronized void decrease(){
            while (num==0){
                try {
                    System.out.println("已经消费完，等待生产者生产");
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            num--;
            System.out.println("消费者消费，数量-1");
            notifyAll();
        }

    }
    static class Producer extends Thread{
        private Product product;

        public Producer(Product product) {
            this.product = product;
        }

        @Override
        public void run() {
            while (true) {
                product.increase();
            }
        }
    }
    static class Consumer extends Thread{
        private Product product;

        public Consumer(Product product) {
            this.product = product;
        }

        @Override
        public void run() {
            while (true){
                product.decrease();
            }
        }
    }

    public static void main(String[] args) {
        Product product = new Product(10);
        Producer producer1 = new Producer(product);
        Producer producer2 = new Producer(product);
        Consumer consumer = new Consumer(product);
        producer1.start();
        producer2.start();
        consumer.start();
    }

}
