package cn.wangjie.learn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2019-10-31 09:50
 **/
public class LogHandlerJdkDynamicProxy implements InvocationHandler {
    //被代理对象，实际的执行者
    Object target;

    public Object getInstance(Object target) {
        this.target = target;
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法执行前，代理输出");
        Object result = method.invoke(target, args);
        System.out.println("方法执行后，代理输出");
        return result;
    }

}
