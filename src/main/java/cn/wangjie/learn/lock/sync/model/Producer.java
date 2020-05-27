package cn.wangjie.learn.lock.sync.model;

public class Producer extends Thread{
    private IProduct IProduct;

    public Producer(IProduct IProduct) {
        this.IProduct = IProduct;
    }

    @Override
    public void run() {
        while (true) {
            IProduct.increase();
        }
    }
}