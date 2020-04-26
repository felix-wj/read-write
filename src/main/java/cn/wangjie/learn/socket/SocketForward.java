package cn.wangjie.learn.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @program: spring-learn
 * @description:
 * @author: WangJie
 * @create: 2020-04-23 20:55
 **/
@Slf4j
public class SocketForward {

    private Socket serverProxy;
    private Socket clientProxy;
    private static final int SIZE=1024*1024;
    public SocketForward(Socket serverProxy, String ip, Integer port) {
        this.serverProxy = serverProxy;

        clientProxy = new Socket();
        clientProxy = new Socket();
        // 设置reuseAddress为true
        try {
            clientProxy.setReuseAddress(true);
            clientProxy.connect(new InetSocketAddress(ip, port));
        } catch (Exception e) {
            throw new RuntimeException("创建socket失败");
        }
    }

    public void run() throws IOException {
        ReadAndWriteThread realClientSend = new ReadAndWriteThread("客户端发送：",serverProxy.getInputStream(), clientProxy.getOutputStream());
        realClientSend.start();

        ReadAndWriteThread realServerSend = new ReadAndWriteThread("服务端发送：",clientProxy.getInputStream(), serverProxy.getOutputStream());
        realServerSend.start();
    }
}
