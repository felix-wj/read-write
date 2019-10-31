package cn.wangjie.learn.proxy;

import org.junit.Test;

import java.io.IOException;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2019-10-31 09:54
 **/
public class ProxyTest {

    int n  = 5000;
    int t = 100;
    @Test
    public void jdkDynamicProxy() {
        UserService userService = new UserServiceImpl();
        int total = 0;
        for (int j = 0; j <t ; j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i <n ; i++) {
                UserService proxyUserService = (UserService) new LogHandlerJdkDynamicProxy().getInstance(userService);
                proxyUserService.print();
            }
            total+=(System.currentTimeMillis()-start);
        }
        System.out.println(total/t);

    }

    @Test
    public void lombdaProxy() throws IOException, InterruptedException {
        UserService userService = new UserServiceImpl();
        int total = 0;
        for (int j = 0; j <t ; j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i <n ; i++) {
                LogHandlerLombda.log(() -> {
                    userService.print();
                    return null;
                });
            }
            total+=System.currentTimeMillis()-start;
        }
        System.out.println(total/t);
    }

    @Test
    public void cglibProxy(){
        int total = 0;
        for (int j = 0; j <t ; j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i <n ; i++) {
               UserService userService = (UserService)new LogHandlerCGLibProxy().getInstance(UserServiceImpl.class);
               userService.print();
            }
            total+=System.currentTimeMillis()-start;
        }
        System.out.println(total/t);
    }
}
