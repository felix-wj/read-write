package cn.wangjie.learn;

import org.junit.Test;

/**
 * @program: learn
 * @description: 初始化测试
 * @author: WangJie
 * @create: 2019-12-21 19:50
 **/
public class InitTest {
    @Test
    public void testInit(){
        Student student = new Student();
    }
}
class Person{
    static {
        System.out.println("父类静态代码块");
    }
    {
        System.out.println("父类代码块");
    }

    public Person() {
        System.out.println("父类构造方法");
    }
}
class Student extends Person{
    static {
        System.out.println("子类静态代码块");
    }
    {
        System.out.println("子类代码块");
    }

    public Student() {
        System.out.println("子类构造方法");
    }
}