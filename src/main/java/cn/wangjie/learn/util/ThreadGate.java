package cn.wangjie.learn.util;

/**
 * @program: read-write
 * @description: 可重复关闭的阀门
 * @author: WangJie
 * @create: 2019-02-27 11:43
 **/

public class ThreadGate {
    private boolean isOpen;
    private  int generation ;


    public ThreadGate(){
        isOpen = false;
        generation = 0;
    }
    public void close(){
        isOpen = false;
    }
    public void open(){
        isOpen = true;
        ++generation;
        notifyAll();
    }
    public void await(){
        int arrivalGeneration = generation;
        if (!isOpen&&arrivalGeneration==generation){
            await();
        }
    }

}
