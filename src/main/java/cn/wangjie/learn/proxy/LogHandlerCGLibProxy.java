package cn.wangjie.learn.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2019-10-31 16:36
 **/
@Slf4j
public class LogHandlerCGLibProxy implements MethodInterceptor {

    public Object getInstance(Class<?> clazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("cglib代理 方法执行前");
        Object result = methodProxy.invokeSuper(o, objects);
        log.info("cglib代理 方法执行后");
        return result;
    }
}
