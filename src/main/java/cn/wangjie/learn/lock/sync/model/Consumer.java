package cn.wangjie.learn.lock.sync.model;


public class Consumer extends Thread{
    private IProduct IProduct;

    public Consumer(IProduct IProduct) {
        this.IProduct = IProduct;
    }

    @Override
    public void run() {
        while (true){
            IProduct.decrease();
        }
    }
}