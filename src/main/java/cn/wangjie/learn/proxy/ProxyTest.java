package cn.wangjie.learn.proxy;


import org.springframework.cglib.core.DebuggingClassWriter;

import java.io.File;
import java.net.URL;

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

        URL url = userService.getClass().getClassLoader().getResource("./cn/wangjie/learn");
/*        File classpath = new File(url.getFile());

        for (File file : classpath.listFiles()) {
            if (file.isFile()){
                System.out.println(file.getName());
            }
        }*/
        System.out.println(url.getFile());
        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,this.getClass().)
    }




}
