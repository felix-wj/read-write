package cn.wangjie.learn.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2020-04-24 11:33
 **/
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(20000);
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            new Thread(() -> {
                byte[] bytes = new byte[1024];
                try {
                    while (inputStream.read(bytes) > 0) {
                        System.out.println("服务端收到消息，写回");
                        outputStream.write(bytes);
                        outputStream.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
