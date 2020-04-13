package cn.wangjie.learn;

import java.util.PriorityQueue;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2020-04-13 20:07
 **/
public class GiveMeK {
    // 1.add necessary fields
    private PriorityQueue<Integer> queue ;

    private int k;

    // 2.construct an instance of GiveMeK class
    public GiveMeK(int k, int[] stream) {
        this.k = k;
        queue = new PriorityQueue(k);
        for(int i: stream){
            showMe(i);
        }
    }

    // 3.showMe adds a new value to the stream and returns the Kth largest element seen so far
    public int showMe(int value) {
        if(queue.size()<k){
            queue.offer(value);
        }else{
            int i = queue.peek();
            if(i<value){
                queue.poll();
                queue.offer(value);
            }
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        int[] a = {7,3,5,8};
        GiveMeK giveMeK = new GiveMeK(2,a);
        System.out.println(giveMeK.showMe(2));
        System.out.println(giveMeK.showMe(9));
        System.out.println(giveMeK.showMe(9));
        System.out.println(giveMeK.showMe(1));
    }
}
