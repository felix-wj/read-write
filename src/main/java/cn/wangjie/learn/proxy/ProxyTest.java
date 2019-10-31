package cn.wangjie.learn.proxy;


import org.springframework.cglib.core.DebuggingClassWriter;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2019-10-31 09:54
 **/
public class ProxyTest {


    public static void main(String[] args) {
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        UserService userService = new UserServiceImpl();
        UserService proxyUserService = (UserService)new LogHandlerJdkDynamicProxy().getInstance(userService);
        proxyUserService.print();


        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,this.getClass().)
    }
}
