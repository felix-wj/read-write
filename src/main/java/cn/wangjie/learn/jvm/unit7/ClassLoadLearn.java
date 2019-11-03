package cn.wangjie.learn.jvm.unit7;

/**
 * @program: learn
 * @description: 类加载
 * @author: WangJie
 * @create: 2019-11-03 14:08
 **/
public class ClassLoadLearn {

    public static void main(String[] args) {
        System.out.println("使用类静态成员 不触发加载");
        System.out.println(SuperClass.HELLOWORLD);
        System.out.println("使用类对象，触发类加载，但不初始化");
        System.out.println(ConstSubClass.class);
        System.out.println("使用new生成对象 触发初始化");
        System.out.println(new ConstClass());
        System.out.println("第二次使用new生成对象 ");
        System.out.println(new ConstClass());

    }
}

class SuperClass {
    static {
        System.out.println("SuperClass init");
    }
    public static final String HELLOWORLD = "hello world";
}
class SubClass extends SuperClass{
    static{
        System.out.println("Subclass init ");
    }

}