package cn.wangjie.learn.collection;

/**
 * @program: read-write
 * @description: 数组实现队列
 * @author: WangJie
 * @create: 2019-03-03 21:45
 **/
public class MyQueue<T> {
    private Object[] queue;
    private int in;
    private int out;
    private int size;
    private int length;

    MyQueue(int size) {
        queue = new Object[size];
        in = out = length = 0;
        this.size = size;
    }

    public boolean offerLast(T obj) {
        if (in == out && queue[in] != null) {
            return false;
        }
        queue[in] = obj;
        in = (in + 1) % size;
        return true;
    }

    public T pollFirst() {
        T target = get(out);
        queue[out] = null;
        if (in == out && target == null) {
            throw new RuntimeException("当前队列无元素");
        }
        out = (out+1)%size;
        return target;

    }

    private T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) queue[index];
    }

    public static void main(String[] args) {
        MyQueue<String> queue = new MyQueue<>(3);
        queue.offerLast("a");
        queue.offerLast("b");
        queue.offerLast("c");
        queue.offerLast("d");
        System.out.println(queue.pollFirst());
        System.out.println(queue.pollFirst());
        System.out.println(queue.pollFirst());
        System.out.println(queue.pollFirst());


    }
}
