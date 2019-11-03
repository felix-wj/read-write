package cn.wangjie.learn.jvm.unit7;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2019-11-03 14:46
 **/
public class ConstSubClass extends ConstClass {
    static {
        System.out.println("const sub class init ");
    }
}
