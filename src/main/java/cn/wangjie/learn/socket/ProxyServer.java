package cn.wangjie.learn.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2020-04-24 15:17
 **/
public class ProxyServer {

    private static final String ip = "localhost";
    private static final int port = 20000;
    public static void main(String[] args) throws IOException {
        // 1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
        ServerSocket serverSocket = new ServerSocket(12345);
        Socket socket = null;
        // 记录客户端的数量
        int count = 0;
        System.out.println("***服务器即将启动，等待客户端的连接***");
        // 循环监听等待客户端的连接
        while (true) {
            // 调用accept()方法开始监听，等待客户端的连接
            socket = serverSocket.accept();
            SocketForward socketForward = new SocketForward(socket,ip,port);
            socketForward.run();
            count++;// 统计客户端的数量
            System.out.println("客户端的数量：" + count);
        }


    }
}
